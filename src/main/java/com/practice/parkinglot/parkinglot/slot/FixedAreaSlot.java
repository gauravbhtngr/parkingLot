package com.practice.parkinglot.parkinglot.slot;

/**
 * FixedArea slots to handle particular scenario 
 */
public class FixedAreaSlot implements Slot {
	private Integer slotNo;

	public FixedAreaSlot(Integer slotNo) {
		this.slotNo = slotNo;
	}
	@Override
	public int getSlotNo() {
		return this.slotNo;
	}

	public void setSlotNo(int slotNo) {
		this.slotNo = slotNo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		return slotNo == ((FixedAreaSlot) o).getSlotNo();
	}

	@Override
	public int hashCode() {
		return slotNo;
	}
	
}
