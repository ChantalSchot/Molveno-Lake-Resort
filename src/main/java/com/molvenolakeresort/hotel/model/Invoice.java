package com.molvenolakeresort.hotel.model;

import java.util.ArrayList;
import java.util.List;

public class Invoice {
	//Cost is in cents, 100 means 1 value of currency (e.g. Dollar or Chinese Yuan)
	private int totalCost;
	private int totalPaid;
	private boolean paymentComplete;
	private String paymentMethod;
	private List<InvoiceItem> invoiceItems;

	Invoice() {
		invoiceItems = new ArrayList<>();
	}

	public int getTotalCost() {
		return totalCost;
	}

	public int getTotalPaid() {
		return totalPaid;
	}

	public void setTotalPaid(int totalPaid) {
		this.totalPaid = totalPaid;
	}

	public boolean isPaymentComplete() {
		return paymentComplete;
	}

	public void setPaymentComplete(boolean paymentComplete) {
		this.paymentComplete = paymentComplete;
	}

	public void pay() {
		if(isPaymentComplete()) {
			System.out.println("Customer already paid.");
		} else if(getTotalCost() <= getTotalPaid()) {
			System.out.println("Customer already paid.");
			setPaymentComplete(true);
		} else if(paymentMethod.isEmpty()) {
			System.out.println("Payment method not yet set!");
		} else {
			System.out.println("Paying with " + this.getPaymentMethod() + " a total of " + getTotalCost());
			this.setTotalPaid(getTotalCost());
			this.setPaymentComplete(true);
		}
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public List<InvoiceItem> getInvoiceItems() {
		return invoiceItems;
	}

	public InvoiceItem getInvoiceItem(String itemName) {
		for(InvoiceItem item : invoiceItems) {
			if(item.getName().equals(itemName)) {
				return item;
			}
		}
		return null; //ToDo: Make this in a 404 error
	}

	public void postInvoiceItem(String itemName, int cost, int amount) {
		//Cost is in cents! Devide by 100 to get full cost
		for(InvoiceItem item : invoiceItems) {
			if(item.getName() == itemName) {
				putInvoiceItem(itemName, cost, amount);
				break;
			}
		}
		InvoiceItem invoiceItem = new InvoiceItem(itemName, cost, amount);
		this.invoiceItems.add(invoiceItem);
		totalCost += invoiceItem.getPrice()*invoiceItem.getAmount();
		setPaymentComplete(false);
	}

	public void putInvoiceItem(String itemName, int cost, int amount) {
		//Cost is in cents! Devide by 100 to get full cost
		for(InvoiceItem item : invoiceItems) {
			if(item.getName() == itemName) {
				item.setAmount(amount);
				item.setPrice(cost);
				setPaymentComplete(false);
				totalCost += item.getPrice();
			}
		}
	}

	public void deleteInvoiceItem(String itemName) {
		//Cost is in cents! Devide by 100 to get full cost
		for(InvoiceItem item : invoiceItems) {
			if(item.getName() == itemName) {
				invoiceItems.remove(item);
			}
		}
	}
}
