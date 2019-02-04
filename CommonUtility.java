package com.ofos.item.common;

public class CommonUtility {
	
	
	public static  Long[] convertToIntarray(String[] parameterValues) {
		Long[] itemids = null;
		if (parameterValues.length == 0) {
			return itemids;
		}
		itemids = new Long[parameterValues.length];
		for (int i = 0; i < parameterValues.length; i++) {
			System.out.println("Ids from UI " + parameterValues[i]);
			Long itemid = Long.parseLong(parameterValues[i]);
			itemids[i] = itemid;
		}
		return itemids;
	}

}
