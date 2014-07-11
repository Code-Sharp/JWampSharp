package co.codesharp.jwampsharp.rpc;

/**
 * Indicates how to treat results of type Collection
 */
public enum CollectionResultTreatment
{
    /**
     * Indicates that results of type Collection are treated as
     * a single return value.
     */
    SINGLE_VALUE,
    /**
     * Indicates that results of type Collection are treated as
     * a multiple return value - i.e. as the arguments of the result.
     */
    MULTIVALUED
}