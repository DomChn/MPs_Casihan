/**************************************************************
*************
This is to certify that this project is my own work, based on my personal efforts
in studying and applying the concepts learned. I have constructed the
functions and their respective algorithms and corresponding code by myself.
The program was run, tested, and debugged by my own efforts. I further
certify that I have not copied in part or whole or otherwise plagiarized the
work of other students and/or persons.
Sofron Dominic J. Casihan, DLSU ID# 12410675
***************************************************************
************/

/*
	Description: The program will simulate a produce-buy-and-sell type game with changing generation costs, weekly trends, and daily prices.
	Programmed by: Sofron Dominic J. Casihan - S24A
	Last modified: 11/24/2024
	Version: 5
	Acknowledgements: https://www.kernel.org/doc/html/v4.10/process/coding-style.html 
			  https://www.tutorialspoint.com/rand-and-srand-in-c
*/

#include <stdio.h>
#include<stdlib.h>
#include<time.h>

/* 	This function checks if the player has met the certain goal required to win the game. 
	Displays "Congratulations" if goal is met, otherwise, displays a lose message.
	
	Precondition: eS and rS are non-negative values
	@param eS - current Energon count
	@param rS - current Reycle stock
	@returns nothing.
*/
void 
checkWinCon (int eS, int rS) 
{
	if (eS >= 1000000) { 
		printf ("Congratulations! Soundwave is set for retirement! He can now sit back, and relax!\n");
		printf ("Thank you for playing!\n");
	} else if (eS < 800 && rS == 0) {
		printf ("Unfortunately, you do not have enough Energon to produce even a single stack!");
		printf ("\nGame shutting down...\n");
	} else {
		printf ("How unfortunate! Soundwave was not able to reach his goal of a million energon!");
		printf("\nHis unwise financial decisions are to blame!\n");
	}
}

/* 	This function checks if the char value is valid.
	@param choice - a char value inputted by the user.
	@returns 1 if character is Y or N. Returns 0 if otherwise.
*/
int 
chCheck (char choice) 
{
	if (choice == 'n' || choice == 'N' || choice == 'Y' || choice == 'y')
		return 1;	
	else 
		return 0;
}

/* 	This function asks the user to input the number of recyclce stacks they want to sell. They are then asked to confirm afterwards. 
	If inputted data is valid and confirmed, Energon storage and recycle stocks will be changed accordingly. Otherwise, 
	transaction is looped.
	Precondition: *eS and *rS are non-negative values 
	@param *rS - current recycle stocks.
	@param *eS - current Energon count
	@returns nothing
*/
void 
recycle (int *rS, int *eS) 
{
	int recS;
	int recR;
	char rCh;
	
	printf ("It is currently recycle day and you have recycle stacks!\n");
	printf ("Please note that you can only sell your recycle stacks every Day 2 (Monday).\n");
	do { /*This is where the input loop of the function starts*/
		printf ("\nHow many stacks would you like to sell? (Current stock: %d) ", *rS);
		scanf ("%d", &recS);
		if (recS < 0) {
			printf ("Invalid number! Please try again!"); /*if user input is negative*/
		} else if (recS > *rS) {
			printf("Exceeds current stock! Try again!\n"); /*if user input is more than owned recycle stacks*/
		} else {
			recR = recS * 200;
			printf ("You are about to sell %d stack/s for %d Energon. Proceed?(y/n) ", recS, recR);
			scanf (" %c", &rCh);
			if (chCheck (rCh) == 0)
				printf ("Invalid input! Please try again!\n");
		}	
	} while (rCh !='y' && rCh !='Y');
	*eS += recR; /*Necessary values changed at this point*/
	*rS -= recS;
	printf ("\nRecycle successful! %d Energon has been added! New balance is %d.\n\n\n", recR, *eS);
}

/* 	The function returns a random number for the daily price depending on the weekly trend.
	Precondition: cost is a non-negative value. trend is a number from 1 to 3; 
	@param cost - current week's generation cost.
	@param trend - current week's trend
	@returns dailyP - randomly generated price according to weekly trend
*/
int 
setPrice (int cost, int trend) 
{
	int nomi;
	int dailyP;
	
	switch (trend) {
	case 1: 
		dailyP = rand () % (cost - 29) + 20; /*generates a random number: min value of 20 and max value of cost - 10 if trend = 1*/
		break;
			
	case 2:
		nomi = cost * 105 / 100;
		dailyP = rand () % (nomi - 80 + 1) + 80; /*generates a random number: min value of 80 and max value of cost * 1.05 if trend = 2*/
		break;
			
	case 3:
		dailyP = rand () % (cost * 4 - (cost - 1)) + cost; /*generates a random number: min value of cost and max value of cost * 4 if trend = 3*/
		break;
	}
	
	return dailyP;
}

/* 	The function handles the sales transaction every day. Implements setPrice() to generate a price according to weekly trend. Afterwards asks 
	user for input and confirmation. If data inputted is valid and confirmed, current energon storage and stacks are changed accordingly. 
	Otherwise, transaction is looped. If there are no stacks left to sell, only displayes the price.
	Precondition: all parameters are non-negative and trend is a number from 1 to 3 
	@param *eS - current energon count
	@param *stacks - current energon stacks
	@param cost - current week's generation cost
	@param trend - current week's trend
	@returns nothing
*/
void 
swindle (int *eS, int *stacks, int cost, int trend) 
{
	int price;
	int totP;
	int sellC;
	int earnings;
	char sCh;
	
	printf("\n%d\n", trend);
	price = setPrice (cost, trend); /*sets the price for the day according to weekly trend*/
	totP = price * 10;
	printf ("Swindle is buying Energon cubes for %d per cube. You can earn %d per stack.\n", price, totP);
	if (*stacks > 0) { 
	 	do { /*This is where the input loop of the function starts*/
			printf ("\nHow many stacks do you wish to sell to Swindle? ");
			scanf ("%d", &sellC);
			if (sellC < 0) {
				printf ("Invalid number! Please try again!\n"); /*if user input is negative*/
				sCh='n';
			} else if (sellC <= *stacks) {
				printf ("%d stack/s are about to be sold, proceed?(y/n) ", sellC); 
				scanf (" %c", &sCh);
				if (chCheck (sCh) == 0) 
					printf ("Invalid input! Please try again!\n");
			} else {
				printf ("You do not have that many stacks! Please try again!\n"); /*if user input is more than owned stacks*/
				sCh='n';
			}		
		} while (sCh != 'Y' && sCh != 'y');
		earnings = sellC * totP;
		printf ("%d stack/s sold.", sellC);
		printf ("\nYou earned %d Energon.\n\n\n", earnings);
		*eS += earnings; /*Necessary values changed at this point*/
		*stacks -= sellC;
	}
	else { /*Executes if there are no stacks left for the week*/
		printf ("\nThere are currently no stacks! No further sales can be transacted.\n");
		printf ("Only the price for this day will be displayed.\n\n\n");
	} 
}

/* 	The function handles the production transaction every Sunday (Day 1). It asks the user how many stacks they want to produce and their confirmation
	If data inputted is valid and confirmed, current energon storage and stacks are changed accordingly. Otherwise, transaction is looped.
	Precondition: all parameters are non-negative and trend is a number from 1 to 3 
	@param *stacks - current energon stacks
	@param *eS - current energon count
	@param cost - current week's generation cost
	@param totC - cost*10
	@returns nothing
*/
void 
produce (int *stacks, int *eS, int cost, int totC) 
{
	int sC;
	int fC;
	char ch;
	
	printf ("Production cost for this week is %d Energon for 1 cube. It will cost %d Energon to produce 1 stack.\n\n", cost, totC);
	do { /*This is where the input loop of the function starts*/
		printf ("How many stack/s do you wish to produce for this week?: ");
		scanf ("%d", &sC);
		fC = sC * totC;
		if (sC < 0) {
			printf ("Invalid number! Select again!\n\n"); /*if user input is negative*/
		} else if (*eS >= fC) {
			printf ("%d stack/s will cost %d Energon, proceed?(y/n) ", sC, fC);
			scanf (" %c", &ch);
			if(chCheck (ch) == 0)
				printf ("Invalid input! Please try again!\n\n");
		} else {
			printf ("Unfortunately, that is going to cost %d. Please select again.\n\n", fC); /*if desired stack cost exceeds energon count*/
		}
	} while (ch != 'Y' && ch != 'y');
	printf ("%d stacks produced.\n\n\n", sC);
	*stacks += sC; /*Necessary values changed at this point*/
	*eS -= (sC*totC);
}

int main() {
	int d;
	int wK; 
	int rS = 0; 
	int stacks = 0;
	int eS = 10000;
	int cost;
	int totC;
	int trend;
	char devCh;
	
	srand(time(0));
	
	/*Dev feature activation*/
	do { 
		printf ("Activate dev feature?(y/n) ");
		scanf (" %c", &devCh);
		if (chCheck(devCh) == 0) {
			printf ("Invalid input! Try again!\n");
		} else if (devCh=='Y'||devCh=='y') {
			do {	
				printf ("Enter desired week: ");
				scanf ("%d", &wK);
				printf ("Enter starting Energon stock: ");
				scanf ("%d", &eS);
				if (wK <= 0 || wK > 10 ||  eS<0) 
					printf("At least one invalid number! Please try again\n\n");
			} while (wK <= 0 || wK > 10 || eS<0);
		} else {
			wK = 1;
		}
	} while (chCheck (devCh) == 0);
	
	printf ("\n");
	
	/*Week loop*/
	while (wK <= 10) { 
		cost = rand () % 41 + 80; /*generates weekly price: 80 to 120*/
		totC = cost * 10;
		trend = rand () % 3 + 1; /*generates weekly trend: 1 to 3*/
		/*Day loop*/
		for (d = 1; d <= 7; d++) {
			printf ("Week %d Day %d\n", wK, d);
			printf ("Energon Storage: %d Stacks: %d\n\n", eS, stacks);
			if (d == 1)  {
				produce (&stacks, &eS, cost, totC);
			} else if (d >= 2 && d <= 7) {
				if (d == 2 && rS > 0)
					recycle(&rS, &eS);
				swindle (&eS, &stacks, cost, trend);
			}
		}
		rS += stacks;
		printf ("%d stack/s have been moved to the recycle pile!\nCurrent recyle pile: %d stacks.\n\n", stacks, rS);
		stacks = 0;
		if (eS < 800 && rS == 0) {
			d = 8;
			wK = 11;
		}
		printf ("\n");
		wK++;
	}
	checkWinCon (eS, rS);
	
	return 0;
}
