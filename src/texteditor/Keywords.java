package texteditor;

import java.util.ArrayList;

/**
 * <h1>A class to store the programming language keywords and
 * provide access to them.</h1>
**/

public class Keywords {

    private String[] supportedLanguages = {".s",".asm"};
    
    private String[] s= {"lb","lh","lw","lbu","lhu","sb","sh","sw","sll","slli",
    		"srl","srli","sra","srai","add","addi","sub","lui","auipc","xor","xori",
    		"or","ori","and","andi","slt","slti","sltu","sltiu","beq","bne","blt",
    		"bge","bltu","bgeu","jal","jalr","fence","fence.i","scall","sbreak",
    		"rdcycle","rdcycleh","rdcycleh","rdtime","rdtimeh","rdinstret",
    		"rdinstreth","csrrw","csrrs","csrrc","csrrwi","csrrsi","csrrci",
    		"ecall","ebreak","eret","mrts","mrth","hrts","wfi","sfence.vm",
    		"c.lw","c.lwsp","c.ld","c.ldsp","c.lq","c.lqsp","c.sw","c.swsp",
    		"c.sd","c.sdsp","c.sq","c.sqsp","c.add","c.addw","c.addi","c.addiw",
    		"c.addi16sp","c.addi4spn","c.li","c.lui","c.mv","c.sub","c.slli",
    		"c.beqz","c.bnez","c.j","c.jr","c.jal","c.jalr","c.ebreak","mul",
    		"mulh","mulhsu","mulhu","div","divu","rem","remu","lr.w","sc.w",
    		"amoswap.w","amoadd.w","amoxor.w","amoand.w","amoor.w","amomin.w",
    		"amomax.w","amominu.w","amomaxu.w","fmv.{h|s}.x","fmv.x.{h|s}",
    		"fcvt.{h|s|d|q}.w","fcvt.{h|s|d|q}.wu","fcvt.w.{h|s|d|q}",
    		"fcvt.wu.{h|s|d|q}","fl{w,d,q}","fs{w,d,q}","fadd.{s|d|q}",
    		"fsub.{s|d|q}","fmul.{s|d|q}","fdiv.{s|d|q}","fsqrt.{s|d|q}",
    		"fmadd.{s|d|q}","fmsub.{s|d|q}","fnmsub.{s|d|q}","fnmadd.{s|d|q}",
    		"fsgnj.{s|d|q}","fsgnjn.{s|d|q}","fsgnjx.{s|d|q}","fmin.{s|d|q}",
    		"fmax.{s|d|q}","feq.{s|d|q}","flt.{s|d|q}","fle.{s|d|q}",
    		"fclass.{s|d|q}","frcsr","frrm","frflags","fscsr","fsrm",
    		"fsflags","fsrmi","fsflagsi","mul{w|d}","div{w|d}","rem{w|d}",
    		"remu{w|d}","lr.{d|q}","sc.{d|q}","amoswap.{d|q}","amoadd.{d|q}",
    		"amoxor.{d|q}","amoand.{d|q}","amoor.{d|q}","amomin.{d|q}",
    		"amomax.{d|q}","amominu.{d|q}","amomaxu.{d|q}","fmv.{d|q}.x",
    		"fmv.x.{d|q}","fcvt.{h|s|d|q}.{l|t}","fcvt.{h|s|d|q}.{l|t}u",
    		"fcvt.{l|t}.{h|s|d|q}","fcvt.{l|t}u.{h|s|d|q}","main","loop","zero"};
    
    private String[] asm= {"lb","lh","lw","lbu","lhu","sb","sh","sw","sll","slli",
    		"srl","srli","sra","srai","add","addi","sub","lui","auipc","xor","xori",
    		"or","ori","and","andi","slt","slti","sltu","sltiu","beq","bne","blt",
    		"bge","bltu","bgeu","jal","jalr","fence","fence.i","scall","sbreak",
    		"rdcycle","rdcycleh","rdcycleh","rdtime","rdtimeh","rdinstret",
    		"rdinstreth","csrrw","csrrs","csrrc","csrrwi","csrrsi","csrrci",
    		"ecall","ebreak","eret","mrts","mrth","hrts","wfi","sfence.vm",
    		"c.lw","c.lwsp","c.ld","c.ldsp","c.lq","c.lqsp","c.sw","c.swsp",
    		"c.sd","c.sdsp","c.sq","c.sqsp","c.add","c.addw","c.addi","c.addiw",
    		"c.addi16sp","c.addi4spn","c.li","c.lui","c.mv","c.sub","c.slli",
    		"c.beqz","c.bnez","c.j","c.jr","c.jal","c.jalr","c.ebreak","mul",
    		"mulh","mulhsu","mulhu","div","divu","rem","remu","lr.w","sc.w",
    		"amoswap.w","amoadd.w","amoxor.w","amoand.w","amoor.w","amomin.w",
    		"amomax.w","amominu.w","amomaxu.w","fmv.{h|s}.x","fmv.x.{h|s}",
    		"fcvt.{h|s|d|q}.w","fcvt.{h|s|d|q}.wu","fcvt.w.{h|s|d|q}",
    		"fcvt.wu.{h|s|d|q}","fl{w,d,q}","fs{w,d,q}","fadd.{s|d|q}",
    		"fsub.{s|d|q}","fmul.{s|d|q}","fdiv.{s|d|q}","fsqrt.{s|d|q}",
    		"fmadd.{s|d|q}","fmsub.{s|d|q}","fnmsub.{s|d|q}","fnmadd.{s|d|q}",
    		"fsgnj.{s|d|q}","fsgnjn.{s|d|q}","fsgnjx.{s|d|q}","fmin.{s|d|q}",
    		"fmax.{s|d|q}","feq.{s|d|q}","flt.{s|d|q}","fle.{s|d|q}",
    		"fclass.{s|d|q}","frcsr","frrm","frflags","fscsr","fsrm",
    		"fsflags","fsrmi","fsflagsi","mul{w|d}","div{w|d}","rem{w|d}",
    		"remu{w|d}","lr.{d|q}","sc.{d|q}","amoswap.{d|q}","amoadd.{d|q}",
    		"amoxor.{d|q}","amoand.{d|q}","amoor.{d|q}","amomin.{d|q}",
    		"amomax.{d|q}","amominu.{d|q}","amomaxu.{d|q}","fmv.{d|q}.x",
    		"fmv.x.{d|q}","fcvt.{h|s|d|q}.{l|t}","fcvt.{h|s|d|q}.{l|t}u",
    		"fcvt.{l|t}.{h|s|d|q}","fcvt.{l|t}u.{h|s|d|q}","main","loop","zero"};

    public String[] getSupportedLanguages() {
        return supportedLanguages;
    }

    private String[] brackets = { "{", "(" };
    private String[] bCompletions = { "}", ")" };
    public String[] getSKeywords() {
        return s;
    }
    public String[] getasmKeywords() {
        return asm;
    }
    public ArrayList<String> getbracketCompletions() {
        ArrayList<String> al = new ArrayList<>();
        for(String completion : bCompletions) {
            al.add(completion);
        }
        return al;
    }
    public ArrayList<String> getbrackets() {
        ArrayList<String> al = new ArrayList<>();
        for(String completion : brackets) {
            al.add(completion);
        }
        return al;
    }
    public ArrayList<String> setKeywords(String[] arr) {
        ArrayList<String> al = new ArrayList<>();
        for(String words : arr) {
            al.add(words);
        }
        return al;
    }

}