// Equality
// Structural equality == similar to equals
// Referential equality === two references point to the same object

// Optimization a == null will be automatically translated to a === null

// Only a custom equals(Any?) implementation may affect the behavior of the operator

// When Doubles or Floats aren't statically know the operator will work like
// NaN is equal to itself
// NaN is considered greater than any other element including POSITIVE_INFINITY
// -0.0 is not equal to 0.0.

// To compare two arrays -> contentEquals()