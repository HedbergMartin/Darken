package datapath;

public class Control {
	
	private int output = 0;
	

    public void perform(int instruction) {
    	switch (instruction) {
			case 0:
				output = 0b1000100010;
				break;
			case 2: //Jump
				output = 0b0100000000;
				break;
			case 4: //Beq
				output = 0b0000000101;
				break;
			case 8: //Addi
				//THA FAQ! Either ALUop == 00(lw,sw) or 01(beq), no clue which is right
				output = 0b0010100000;
				break;
			case 13: //Ori
				//THA FAQ! Either ALUop == 00 or 01, no clue which is right
				output = 0b0010100000;
				break;
			case 35: //LW
				output = 0b0011110000;
				break;
			case 43: //SW
				output = 0b0010001000;
				break;
		}
    }

    public boolean isRegDst() {
		return getBit(output, 9);
	}

    public boolean isJump() {
		return getBit(output, 8);
	}

	public boolean isALUSrc() {
		return getBit(output, 7);
	}

	public boolean isMemtoReg() {
		return getBit(output, 6);
	}

	public boolean isRegWrite() {
		return getBit(output, 5);
	}

	public boolean isMemRead() {
		return getBit(output, 4);
	}

	public boolean isMemWrite() {
		return getBit(output, 3);
	}

	public boolean isBranch() {
		return getBit(output, 2);
	}

	public boolean isALUOp1() {
		return getBit(output, 1);
	}

	public boolean isALUOP0() {
		return getBit(output, 0);
	}
	
	private boolean getBit(int value, int position) {
	   return ((value >> position) & 1) == 1;
	}
}
