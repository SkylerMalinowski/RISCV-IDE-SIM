package base;

/* 
 * RV32E :: 32-Bit Embedded Base Instruction Set
 * Standard, Version: 1.9
 */
public class RV32E {
	int xlen = 32;
	int pc = 0;
	int reg[] = new int[16];
	char validExt[] = {'M','A','C'};
}
