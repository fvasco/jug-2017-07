10 GOSUB 1000:REM READ FILE
20 GOSUB 2000:REM SORT ARRAY
30 GOSUB 3000:REM COUNT OCCURRENCES
40 GOSUB 4000:REM PRINT OCCURRENCES
999 END
1000 :
1010 REM READ FILE
1020 T=0:DIM V(100)
1030 OPEN1,8,2,"SOURCE,S,R"
1040 IF ST AND 64 THEN 1090
1050 INPUT#1,X
1060 V(T)=X
1070 T=T+1
1080 GOTO 1040
1090 CLOSE 1
1100 RETURN
2000 :
2010 REM SORT ARRAY
2020 FOR I=0 TO T-1
2030 FOR J=I TO T-1
2040 IF V(I)>V(J) THEN S=V(I):V(I)=V(J):V(J)=S
2050 NEXT
2060 NEXT
2070 RETURN
3000 :
3010 REM COUNT OCCURRENCES
3020 DIM C(100)
3030 FOR I=0 TO T-1
3040 C(V(I))=C(V(I))+1
3050 NEXT
3060 RETURN
4000 :
4010 REM PRINT OCCURRENCES
4020 FOR I=0 TO 100
4030 IF C(I)>0 THEN PRINT"VAL=";I;"  COUNT=";C(I)
4040 NEXT
4050 RETURN
