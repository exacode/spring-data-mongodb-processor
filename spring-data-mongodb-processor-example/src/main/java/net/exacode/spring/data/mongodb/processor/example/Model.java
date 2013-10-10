package net.exacode.spring.data.mongodb.processor.example;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Model {

	/* Fields of type byte */

	private Byte byteWrapperValue;

	private byte byteValue;

	private byte[] byteArray;

	private Byte[] byteWrapperArray;

	private List<Byte> byteList;

	/* Fields of type short */

	private Short shortWrapperValue;

	private short shortValue;

	private short[] shortArray;

	private Short[] shortWrapperArray;

	private List<Short> shortList;

	/* Fields of type int */

	private Integer intWrapperValue;

	private int intValue;

	private int[] intArray;

	private Integer[] intWrapperArray;

	private List<Integer> intList;

	/* Fields of type long */

	private Long longWrapperValue;

	private long longValue;

	private long[] longArray;

	private Long[] longWrapperArray;

	private List<Long> longList;

	/* Fields of type float */

	private Float floatWrapperValue;

	private float floatValue;

	private float[] floatArray;

	private Float[] floatWrapperArray;

	private List<Float> floatList;

	/* Fields of type double */

	private Double doubleWrapperValue;

	private double doubleValue;

	private double[] doubleArray;

	private Double[] doubleWrapperArray;

	private List<Double> doubleList;

	/* Fields of type BigDecimal */

	private BigDecimal bigDecimalValue;

	private BigDecimal[] bigDecimalArray;

	private List<BigDecimal> bigDecimalList;

	/* Fields of type String */

	private String stringValue;

	private String[] stringArray;

	private List<String> stringList;

	/* Fields of type Date */

	private Date dateValue;

	private Date[] dateArray;

	private List<Date> dateList;

	/* Fields of type org.joda.DateTime */

	private DateTime dateTimeValue;

	private DateTime[] dateTimeArray;

	private List<DateTime> dateTimeList;

	/* Fields of type Model */

	private Model modelValue;

	private Model[] modelArray;

	private List<Model> modelList;

	/* Fields of type SubDocument */

	private SubDocument subDocument;

	private SubDocument[] subDocumentArray;

	private List<SubDocument> subDocumentList;

	public Byte getByteWrapperValue() {
		return byteWrapperValue;
	}

	public void setByteWrapperValue(Byte byteWrapperValue) {
		this.byteWrapperValue = byteWrapperValue;
	}

	public byte getByteValue() {
		return byteValue;
	}

	public void setByteValue(byte byteValue) {
		this.byteValue = byteValue;
	}

	public byte[] getByteArray() {
		return byteArray;
	}

	public void setByteArray(byte[] byteArray) {
		this.byteArray = byteArray;
	}

	public Byte[] getByteWrapperArray() {
		return byteWrapperArray;
	}

	public void setByteWrapperArray(Byte[] byteWrapperArray) {
		this.byteWrapperArray = byteWrapperArray;
	}

	public List<Byte> getByteList() {
		return byteList;
	}

	public void setByteList(List<Byte> byteList) {
		this.byteList = byteList;
	}

	public Short getShortWrapperValue() {
		return shortWrapperValue;
	}

	public void setShortWrapperValue(Short shortWrapperValue) {
		this.shortWrapperValue = shortWrapperValue;
	}

	public short getShortValue() {
		return shortValue;
	}

	public void setShortValue(short shortValue) {
		this.shortValue = shortValue;
	}

	public short[] getShortArray() {
		return shortArray;
	}

	public void setShortArray(short[] shortArray) {
		this.shortArray = shortArray;
	}

	public Short[] getShortWrapperArray() {
		return shortWrapperArray;
	}

	public void setShortWrapperArray(Short[] shortWrapperArray) {
		this.shortWrapperArray = shortWrapperArray;
	}

	public List<Short> getShortList() {
		return shortList;
	}

	public void setShortList(List<Short> shortList) {
		this.shortList = shortList;
	}

	public Integer getIntWrapperValue() {
		return intWrapperValue;
	}

	public void setIntWrapperValue(Integer intWrapperValue) {
		this.intWrapperValue = intWrapperValue;
	}

	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public int[] getIntArray() {
		return intArray;
	}

	public void setIntArray(int[] intArray) {
		this.intArray = intArray;
	}

	public Integer[] getIntWrapperArray() {
		return intWrapperArray;
	}

	public void setIntWrapperArray(Integer[] intWrapperArray) {
		this.intWrapperArray = intWrapperArray;
	}

	public List<Integer> getIntList() {
		return intList;
	}

	public void setIntList(List<Integer> intList) {
		this.intList = intList;
	}

	public Long getLongWrapperValue() {
		return longWrapperValue;
	}

	public void setLongWrapperValue(Long longWrapperValue) {
		this.longWrapperValue = longWrapperValue;
	}

	public long getLongValue() {
		return longValue;
	}

	public void setLongValue(long longValue) {
		this.longValue = longValue;
	}

	public long[] getLongArray() {
		return longArray;
	}

	public void setLongArray(long[] longArray) {
		this.longArray = longArray;
	}

	public Long[] getLongWrapperArray() {
		return longWrapperArray;
	}

	public void setLongWrapperArray(Long[] longWrapperArray) {
		this.longWrapperArray = longWrapperArray;
	}

	public List<Long> getLongList() {
		return longList;
	}

	public void setLongList(List<Long> longList) {
		this.longList = longList;
	}

	public Float getFloatWrapperValue() {
		return floatWrapperValue;
	}

	public void setFloatWrapperValue(Float floatWrapperValue) {
		this.floatWrapperValue = floatWrapperValue;
	}

	public float getFloatValue() {
		return floatValue;
	}

	public void setFloatValue(float floatValue) {
		this.floatValue = floatValue;
	}

	public float[] getFloatArray() {
		return floatArray;
	}

	public void setFloatArray(float[] floatArray) {
		this.floatArray = floatArray;
	}

	public Float[] getFloatWrapperArray() {
		return floatWrapperArray;
	}

	public void setFloatWrapperArray(Float[] floatWrapperArray) {
		this.floatWrapperArray = floatWrapperArray;
	}

	public List<Float> getFloatList() {
		return floatList;
	}

	public void setFloatList(List<Float> floatList) {
		this.floatList = floatList;
	}

	public Double getDoubleWrapperValue() {
		return doubleWrapperValue;
	}

	public void setDoubleWrapperValue(Double doubleWrapperValue) {
		this.doubleWrapperValue = doubleWrapperValue;
	}

	public double getDoubleValue() {
		return doubleValue;
	}

	public void setDoubleValue(double doubleValue) {
		this.doubleValue = doubleValue;
	}

	public double[] getDoubleArray() {
		return doubleArray;
	}

	public void setDoubleArray(double[] doubleArray) {
		this.doubleArray = doubleArray;
	}

	public Double[] getDoubleWrapperArray() {
		return doubleWrapperArray;
	}

	public void setDoubleWrapperArray(Double[] doubleWrapperArray) {
		this.doubleWrapperArray = doubleWrapperArray;
	}

	public List<Double> getDoubleList() {
		return doubleList;
	}

	public void setDoubleList(List<Double> doubleList) {
		this.doubleList = doubleList;
	}

	public BigDecimal getBigDecimalValue() {
		return bigDecimalValue;
	}

	public void setBigDecimalValue(BigDecimal bigDecimalValue) {
		this.bigDecimalValue = bigDecimalValue;
	}

	public BigDecimal[] getBigDecimalArray() {
		return bigDecimalArray;
	}

	public void setBigDecimalArray(BigDecimal[] bigDecimalArray) {
		this.bigDecimalArray = bigDecimalArray;
	}

	public List<BigDecimal> getBigDecimalList() {
		return bigDecimalList;
	}

	public void setBigDecimalList(List<BigDecimal> bigDecimalList) {
		this.bigDecimalList = bigDecimalList;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public String[] getStringArray() {
		return stringArray;
	}

	public void setStringArray(String[] stringArray) {
		this.stringArray = stringArray;
	}

	public List<String> getStringList() {
		return stringList;
	}

	public void setStringList(List<String> stringList) {
		this.stringList = stringList;
	}

	public Date getDateValue() {
		return dateValue;
	}

	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}

	public Date[] getDateArray() {
		return dateArray;
	}

	public void setDateArray(Date[] dateArray) {
		this.dateArray = dateArray;
	}

	public List<Date> getDateList() {
		return dateList;
	}

	public void setDateList(List<Date> dateList) {
		this.dateList = dateList;
	}

	public DateTime getDateTimeValue() {
		return dateTimeValue;
	}

	public void setDateTimeValue(DateTime dateTimeValue) {
		this.dateTimeValue = dateTimeValue;
	}

	public DateTime[] getDateTimeArray() {
		return dateTimeArray;
	}

	public void setDateTimeArray(DateTime[] dateTimeArray) {
		this.dateTimeArray = dateTimeArray;
	}

	public List<DateTime> getDateTimeList() {
		return dateTimeList;
	}

	public void setDateTimeList(List<DateTime> dateTimeList) {
		this.dateTimeList = dateTimeList;
	}

	public Model getModelValue() {
		return modelValue;
	}

	public void setModelValue(Model modelValue) {
		this.modelValue = modelValue;
	}

	public Model[] getModelArray() {
		return modelArray;
	}

	public void setModelArray(Model[] modelArray) {
		this.modelArray = modelArray;
	}

	public List<Model> getModelList() {
		return modelList;
	}

	public void setModelList(List<Model> modelList) {
		this.modelList = modelList;
	}

	public SubDocument getSubDocument() {
		return subDocument;
	}

	public void setSubDocument(SubDocument subDocument) {
		this.subDocument = subDocument;
	}

	public SubDocument[] getSubDocumentArray() {
		return subDocumentArray;
	}

	public void setSubDocumentArray(SubDocument[] subDocumentArray) {
		this.subDocumentArray = subDocumentArray;
	}

	public List<SubDocument> getSubDocumentList() {
		return subDocumentList;
	}

	public void setSubDocumentList(List<SubDocument> subDocumentList) {
		this.subDocumentList = subDocumentList;
	}

}
