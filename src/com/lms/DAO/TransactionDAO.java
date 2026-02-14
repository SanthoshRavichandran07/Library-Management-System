package com.lms.DAO;

import com.lms.model.Transactions;

public interface TransactionDAO {
	public void issueBook(Transactions transaction);
	public void returnBook(Transactions transaction);
	public void viewTransactions();
}
