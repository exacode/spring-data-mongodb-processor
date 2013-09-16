package org.springframework.data.mongodb.processor;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.processor.model.MetaModel;

/**
 * Processor that generates meta model for MongoDB documents.
 * <p>
 * Uses {@link Document} annotation to determine the root of the MongoDB
 * document.
 * 
 * @author mendlik
 */
public class DocumentProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {

		if (roundEnv.processingOver()) {
			// We're not interested in the postprocessing round.
			return false;
		}

		Logger logger = new Logger(processingEnv);
		logger.note("Running: " + this.getClass().getCanonicalName());

		// Find unique types that needs meta model
		ModelTypeChooser typeChooser = new ModelTypeChooser(processingEnv);
		Set<TypeElement> modelTypes = new HashSet<TypeElement>();
		for (TypeElement te : annotations) {
			for (Element element : roundEnv.getElementsAnnotatedWith(te)) {
				typeChooser.getDocumentTypes(element.asType(), modelTypes);
			}
		}

		// Create meta model for appropriate types
		MetaModelWriter writer = new MetaModelWriter(processingEnv);
		MetaModelGenerator generator = new MetaModelGenerator(processingEnv,
				modelTypes);
		for (TypeElement typeElement : modelTypes) {
			MetaModel metaModel = generator.analyzeType(typeElement);
			writer.write(metaModel);
		}
		if (!modelTypes.isEmpty()) {
			writer.writeUtilClasses();
		}

		return true;
	}

	@Override
	public Set<String> getSupportedAnnotationTypes() {
		Set<String> supportedTypes = new HashSet<String>();
		supportedTypes.add(Document.class.getCanonicalName());
		return supportedTypes;
	}

	@Override
	public SourceVersion getSupportedSourceVersion() {
		return SourceVersion.latestSupported();
	}

}
