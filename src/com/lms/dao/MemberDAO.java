package com.lms.dao;


public interface MemberDAO {
	void addMember();
	void updateMember();
	void deleteMember();
	void searchMember();
	void viewMember();
	void viewMemberDetails(int id);
}
