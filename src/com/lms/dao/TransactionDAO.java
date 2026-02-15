package com.lms.dao;

import com.lms.model.Transactions;

public interface TransactionDAO {
	public void issueBook(Transactions transaction);
	public void returnBook(Transactions transaction);
	public void viewTransactions();
	public void viewMemberTransactions(int id);
}
