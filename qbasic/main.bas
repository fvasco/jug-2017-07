OPTION BASE 0
DIM v(100) AS INTEGER
DIM tot AS INTEGER
DIM occ(100) AS INTEGER

DECLARE SUB LoadData (filename AS STRING, v() AS INTEGER, t AS INTEGER)
DECLARE SUB QuickSort (v() AS INTEGER, low AS INTEGER, high AS INTEGER)
DECLARE SUB Partition (v() AS INTEGER, low AS INTEGER, up AS INTEGER, j AS INTEGER, i AS INTEGER)
DECLARE SUB CountOccurrences (v() AS INTEGER, t AS INTEGER, occ() AS INTEGER)
DECLARE SUB PrintOccurrences (occ() AS INTEGER)

LoadData "SOURCE", v(), tot
QuickSort v(), 0, tot - 1
CountOccurrences v(), tot, occ()
PrintOccurrences occ()

SUB LoadData (filename AS STRING, v() AS INTEGER, t AS INTEGER)
  f = FREEFILE
  OPEN filename FOR INPUT AS f
  t = 0
  DO WHILE NOT EOF(f)
    INPUT #f, x
    v(t) = x
    t = t + 1
  LOOP
  CLOSE f
END SUB

SUB Partition (v() AS INTEGER, low AS INTEGER, up AS INTEGER, j AS INTEGER, i AS INTEGER)
  DIM pivot AS INTEGER
  DIM pivotIndex AS INTEGER

  pivotIndex = (low + up) / 2
  pivot = v(pivotIndex)
  i = low
  j = up
  DO WHILE v(i) < pivot
    i = i + 1
  LOOP
  DO WHILE v(j) > pivot
    j = j - 1
  LOOP
  DO WHILE i < j - 1
    SWAP v(i), v(j)
    i = i + 1
    j = j - 1
    DO WHILE v(i) < pivot
      i = i + 1
    LOOP
    DO WHILE v(j) > pivot
      j = j - 1
    LOOP
  LOOP
  IF i <= j THEN
    IF i < j THEN SWAP v(i), v(j)
    i = i + 1
    j = j - 1
  END IF
END SUB

SUB QuickSort (v() AS INTEGER, low AS INTEGER, high AS INTEGER)
  DIM lower AS INTEGER
  DIM higher AS INTEGER
  IF (low < high) THEN
    Partition v(), low, high, lower, higher
    IF (lower - low) < (high - higher) THEN
      QuickSort v(), low, lower
      QuickSort v(), higher, high
    ELSE
      QuickSort v(), higher, high
      QuickSort v(), low, lower
    END IF
  END IF
END SUB

SUB CountOccurrences (v() AS INTEGER, t AS INTEGER, occ() AS INTEGER)
  DIM i AS INTEGER
  FOR i = 0 TO t - 1
    occ(v(i)) = occ(v(i)) + 1
  NEXT
END SUB

SUB PrintOccurrences (occ() AS INTEGER)
  DIM i AS INTEGER
  FOR i = LBOUND(occ) TO UBOUND(occ)
    IF occ(i) > 0 THEN PRINT "N."; i, "OCCURRENCES ="; occ(i)
  NEXT
END SUB

