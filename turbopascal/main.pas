PROGRAM JUG201707;

CONST MAXSIZE = 100;
      MAXVAL = 100;

TYPE elem    = 0..MAXVAL;
     index   = 0..MAXSIZE;
     vector  = ARRAY [1..MAXSIZE] of elem;
     occType = ARRAY [elem] of integer;
     fname   = STRING[20];

VAR numbers     : vector;
    nval        : index;
    occurrences : occType;

PROCEDURE LoadData(filename:fname; VAR numbers:vector; VAR tot:index);
VAR f:TEXT;
BEGIN
  Assign(f, filename);
  Reset(f);
  tot := 0;
  WHILE NOT Eof(f) DO BEGIN
    tot := tot+1;
    READLN(f, numbers[tot]);
  END;
  Close(f)
END;

PROCEDURE Partition(VAR numbers:vector; l,u:index; VAR j,i:index);
VAR t,pivot    : elem;
    pivotIndex : index;
BEGIN
  pivotIndex := (l+u) div 2;
  pivot := numbers[pivotIndex];
  i := l;
  j := u;
  WHILE numbers[i] < pivot DO i := i+1;
  WHILE numbers[j] > pivot DO j := j-1;
  WHILE i < j-1 DO BEGIN
    t := numbers[i];
    numbers[i] := numbers[j];
    numbers[j] := t;
    i := i+1;
    j := j-1;
    WHILE numbers[i] < pivot DO i := i+1;
    WHILE numbers[j] > pivot DO j := j-1;
  END;
  IF i <= j THEN BEGIN
    IF i < j THEN BEGIN
      t := numbers[i];
      numbers[i] := numbers[j];
      numbers[j] := t;
    END;
    i := i+1;
    j := j-1;
  END;
END;

PROCEDURE QuickSort(VAR numbers:vector; low,high:index);
VAR lower,higher : index;
BEGIN
  IF low < high THEN BEGIN
    Partition(numbers,low,high,lower,higher);
    IF (lower-low) < (high-higher) THEN BEGIN
      QuickSort(numbers,low,lower);
      QuickSort(numbers,higher,high)
    END ELSE BEGIN
      QuickSort(numbers,higher,high);
      QuickSort(numbers,low,lower)
    END
  END
END;

PROCEDURE CountOccurrences(VAR numbers:vector; tot:index; VAR occurrences:occType);
VAR i:elem;
BEGIN
  FOR i := 1 TO tot DO occurrences[numbers[i]] := occurrences[numbers[i]]+1
END;

PROCEDURE PrintOccurrences(VAR occurrences:occType);
VAR i:elem;
BEGIN
  FOR i := 0 TO MAXVAL DO
    IF occurrences[i] > 0 THEN
      WriteLn('Value: ',i,', Occurrences: ',occurrences[i])
END;

BEGIN
  LoadData('source', numbers, nval);
  QuickSort(numbers, 1, nval);
  CountOccurrences(numbers, nval, occurrences);
  PrintOccurrences(occurrences);
END.