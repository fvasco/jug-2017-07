#include <stdio.h>
#include <stdlib.h>

#define MAXSIZE  100
#define MAXVAL   100
#define LINESIZE 200

typedef int elem;
typedef int index;
typedef int vector[MAXSIZE];
typedef int occType[MAXVAL];

void loadData(char *filename, vector numbers, index *pTot);
void partition(vector numbers, index low, index up, index *pj, index *pi);
void quickSort(vector numbers, index low, index high);
void countOccurrences(vector numbers, index tot, occType occurrences);
void printOccurrences(occType occurrences);

int main(int argc, char *argv[]) {
	vector numbers;
	index nval;
	occType occurrences;

	loadData("source", numbers, &nval);
	quickSort(numbers, 0, nval-1);
	countOccurrences(numbers, nval, occurrences);
	printOccurrences(occurrences);
}

void loadData(char *filename, vector numbers, index *pTot) {
	FILE *fp;
	char line[LINESIZE];

	fp = fopen(filename,"r");
	*pTot = 0;
	while (fgets(line, LINESIZE, fp) != NULL)
		numbers[(*pTot)++] = atoi(line);
	fclose(fp);
}

void partition(vector numbers, index low, index up, index *pj, index *pi) {
	elem t,pivot;
	index pivotIndex;

	pivotIndex = (low+up)/2;
	pivot = numbers[pivotIndex];
	*pi = low;
	*pj = up;
	while (numbers[*pi] < pivot) (*pi)++;
	while (numbers[*pj] > pivot) (*pj)--;
	while (*pi < (*pj)-1) {
		t = numbers[*pi]; numbers[*pi] = numbers[*pj]; numbers[*pj] = t;
		(*pi)++;
		(*pj)--;
		while (numbers[*pi] < pivot) (*pi)++;
		while (numbers[*pj] > pivot) (*pj)--;
	}
	if (*pi <= *pj) {
		if (*pi < *pj) { t = numbers[*pi]; numbers[*pi] = numbers[*pj]; numbers[*pj] = t; }
		(*pi)++;
		(*pj)--;
	}
}

void quickSort(vector numbers, index low, index high) {
	index lower, higher;

	if (low < high) {
		partition(numbers, low, high, &lower, &higher);
		if (lower-low < high-higher) {
			quickSort(numbers, low, lower);
			quickSort(numbers, higher, high);
		} else {
			quickSort(numbers, higher, high);
			quickSort(numbers, low, lower);
		}
	}
}

void countOccurrences(vector numbers, index tot, occType occurrences) {
	index i;
	for (i=0; i<tot; ++i) occurrences[numbers[i]]++;
}

void printOccurrences(occType occurrences) {
	elem i;
	for (i=0; i<MAXVAL; ++i)
		if (occurrences[i] > 0)
			printf("Value: %d, occurrences: %d\n", i, occurrences[i]);
}
