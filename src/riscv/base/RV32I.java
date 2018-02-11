package riscv.base;

import java.util.HashMap;

/*
 *  RV32I :: 32-Bit Integrated Base Instruction Set
 *  Standard, Version: 2.0
 */
public class RV32I {
	public int xlen = 32;
	public int reg[] = new int[32];
	public HashMap<String, String> TypeMap = new HashMap<String, String>();
	
	// Constructor
	RV32I()
	{
		// R-Type
		TypeMap.put("ADD", "R-Type");
		TypeMap.put("SUB", "R-Type");
		TypeMap.put("SLT", "R-Type");
		TypeMap.put("SLTU", "R-Type");
		TypeMap.put("XOR", "R-Type");
		TypeMap.put("OR", "R-Type");
		TypeMap.put("AND", "R-Type");
		
		// R-Immediate
		TypeMap.put("ADDI", "R-Immediate");
		TypeMap.put("SLTI", "R-Immediate");
		TypeMap.put("SLTIU", "R-Immediate");
		TypeMap.put("XORI", "R-Immediate");
		TypeMap.put("ORI", "R-Immediate");
		TypeMap.put("ANDI", "R-Immediate");
		
		// I-Type
		TypeMap.put("LB", "I-Type");
		TypeMap.put("LH", "I-Type");
		TypeMap.put("LW", "I-Type");
		TypeMap.put("LBU", "I-Type");
		TypeMap.put("LHU", "I-Type");
		TypeMap.put("JALR", "I-Type");
		TypeMap.put("SLL", "I-Type");
		TypeMap.put("SRL", "I-Type");
		TypeMap.put("SRA", "I-Type");
		
		// I-Immediate
		TypeMap.put("SLLI", "I-Immediate");
		TypeMap.put("SRLI", "I-Immediate");
		TypeMap.put("SRAI", "I-Immediate");
		
		// S-Type
		TypeMap.put("SB", "S-Type");
		TypeMap.put("SH", "S-Type");
		TypeMap.put("SW", "S-Type");
		
		// B-Type
		TypeMap.put("BEQ", "B-Type");
		TypeMap.put("BNE", "B-Type");
		TypeMap.put("BLT", "B-Type");
		TypeMap.put("BGE", "B-Type");
		TypeMap.put("BLTU", "B-Type");
		TypeMap.put("BGEU", "B-Type");
		
		// U-Type
		TypeMap.put("LUI", "U-Type");
		TypeMap.put("JAL", "U-Type");
		TypeMap.put("AUIPC", "U-Type");
		
		/* Unimplemented Instructions
		 * TypeMap.put("FENCE", "");
		 * TypeMap.put("FENCE.I", "");
		 * TypeMap.put("ECALL", "");
		 * TypeMap.put("EBREAK", "");
		 * TypeMap.put("CSRRWI", "");
		 * TypeMap.put("CSRRSI", "");
		 * TypeMap.put("CSRRCI", "");
		 */
	}
}
