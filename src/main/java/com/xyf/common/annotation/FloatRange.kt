package com.xyf.common.annotation

@kotlin.annotation.Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.LOCAL_VARIABLE, AnnotationTarget.ANNOTATION_CLASS)
annotation class FloatRange(val from: Double = Double.MIN_VALUE, val to: Double = Double.MAX_VALUE, val fromInclusive: Boolean = true, val toInclusive: Boolean = true)