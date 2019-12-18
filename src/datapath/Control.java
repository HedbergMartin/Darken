package datapath;

import java.util.HashMap;
import java.util.Map;

public class Control {

	private boolean RegDst = false;
	private boolean ALUSrc = false;
	private boolean MemtoReg = false;
	private boolean RegWrite = false;
	private boolean MemRead = false;
	private boolean MemWrite = false;
	private boolean Branch = false;
	private boolean ALUOp1 = false;
	private boolean ALUOP0 = false;
	

    public void perform(int instruction) {
    	switch (instruction) {
		case 0:
			RegDst = true;
			ALUSrc = false;
			MemtoReg = false;
			RegWrite = true;
			MemRead = false;
			MemWrite = false;
			Branch = false;
			ALUOp1 = true;
			ALUOP0 = false;
			break;
		case 4: //Beq
			ALUSrc = false;
			RegWrite = false;
			MemRead = false;
			MemWrite = false;
			Branch = true;
			ALUOp1 = false;
			ALUOP0 = true;
			break;
		case 35: //LW
			RegDst = false;
			ALUSrc = true;
			MemtoReg = true;
			RegWrite = true;
			MemRead = true;
			MemWrite = false;
			Branch = false;
			ALUOp1 = false;
			ALUOP0 = false;
			break;
		case 43: //SW
			ALUSrc = true;
			RegWrite = false;
			MemRead = false;
			MemWrite = true;
			Branch = false;
			ALUOp1 = false;
			ALUOP0 = false;
			break;
		}
    }

    public boolean isRegDst() {
		return RegDst;
	}

	public boolean isALUSrc() {
		return ALUSrc;
	}

	public boolean isMemtoReg() {
		return MemtoReg;
	}

	public boolean isRegWrite() {
		return RegWrite;
	}

	public boolean isMemRead() {
		return MemRead;
	}

	public boolean isMemWrite() {
		return MemWrite;
	}

	public boolean isBranch() {
		return Branch;
	}

	public boolean isALUOp1() {
		return ALUOp1;
	}

	public boolean isALUOP0() {
		return ALUOP0;
	}
}
