package net.exacode.spring.data.mongodb.processor.example.exclusion;

import net.exacode.spring.data.mongodb.processor.shared.MetaModel;
import net.exacode.spring.data.mongodb.processor.shared.MetaModelExclude;

@MetaModel
public class ModelWithExclusion {

	@MetaModelExclude
	private ExcludedSubModel excludedSubModel;

	@MetaModelExclude(pathOnly = true)
	private ExcludedSubModel excludedSubModelWithPath;

}
