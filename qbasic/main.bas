OPTION BASE 0
DIM numbers(100) AS INTEGER
DIM tot AS INTEGER
DIM occurrences(100) AS INTEGER

DECLARE SUB LoadData (filename AS STRING, numbers() AS INTEGER, t AS INTEGER)
DECLARE SUB QuickSort (numbers() AS INTEGER, low AS INTEGER, high AS INTEGER)
DECLARE SUB Partition (numbers() AS INTEGER, low AS INTEGER, up AS INTEGER, j AS INTEGER, i AS INTEGER)
DECLARE SUB CountOccurrences (numbers() AS INTEGER, t AS INTEGER, occurrences() AS INTEGER)
DECLARE SUB PrintOccurrences (occurrences() AS INTEGER)

LoadData "SOURCE", numbers(), tot
QuickSort numbers(), 0, tot - 1
CountOccurrences numbers(), tot, occurrences()
PrintOccurrences occurrences()

SUB LoadData (filename AS STRING, numbers() AS INTEGER, t AS INTEGER)
  f = FREEFILE
  OPEN filename FOR INPUT AS f
  t = 0
  DO WHILE NOT EOF(f)
    INPUT #f, x
    numbers(t) = x
    t = t + 1
  LOOP
  CLOSE f
END SUB

SUB Partition (numbers() AS INTEGER, low AS INTEGER, up AS INTEGER, j AS INTEGER, i AS INTEGER)
  DIM pivot AS INTEGER
  DIM pivotIndex AS INTEGER

  pivotIndex = (low + up) / 2
  pivot = numbers(pivotIndex)
  i = low
  j = up
  DO WHILE numbers(i) < pivot
    i = i + 1
  LOOP
  DO WHILE numbers(j) > pivot
    j = j - 1
  LOOP
  DO WHILE i < j - 1
    SWAP numbers(i), numbers(j)
    i = i + 1
    j = j - 1
    DO WHILE numbers(i) < pivot
      i = i + 1
    LOOP
    DO WHILE numbers(j) > pivot
      j = j - 1
    LOOP
  LOOP
  IF i <= j THEN
    IF i < j THEN SWAP numbers(i), numbers(j)
    i = i + 1
    j = j - 1
  END IF
END SUB

SUB QuickSort (numbers() AS INTEGER, low AS INTEGER, high AS INTEGER)
  DIM lower AS INTEGER
  DIM higher AS INTEGER
  IF (low < high) THEN
    Partition numbers(), low, high, lower, higher
    IF (lower - low) < (high - higher) THEN
      QuickSort numbers(), low, lower
      QuickSort numbers(), higher, high
    ELSE
      QuickSort numbers(), higher, high
      QuickSort numbers(), low, lower
    END IF
  END IF
END SUB

SUB CountOccurrences (numbers() AS INTEGER, t AS INTEGER, occurrences() AS INTEGER)
  DIM i AS INTEGER
  FOR i = 0 TO t - 1
    occurrences(numbers(i)) = occurrences(numbers(i)) + 1
  NEXT
END SUB

SUB PrintOccurrences (occurrences() AS INTEGER)
  DIM i AS INTEGER
  FOR i = LBOUND(occurrences) TO UBOUND(occurrences)
    IF occurrences(i) > 0 THEN PRINT "N."; i, "OCCURRENCES ="; occurrences(i)
  NEXT
END SUB

