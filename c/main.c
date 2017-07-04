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
index partition(vector arr, index left, index right);
void quickSort(vector arr, index left, index right);
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

void quickSort(vector arr, index left, index right) {
        index index = partition(arr, left, right);
        if (left < index - 1)
            quickSort(arr, left, index - 1);
        if (index < right)
            quickSort(arr, index, right);
}

index partition(vector arr, index left, index right) {
        index i = left, j = right;
        elem pivot = arr[(left + right) / 2];

        while (i <= j) {
            while (arr[i] < pivot)
                i++;

            while (arr[j] > pivot)
                j--;

            if (i <= j) {
                elem tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }
        return i;
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
