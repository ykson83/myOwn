import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AccountTest {
	Account account = new Account();  //account라는 계좌를 생성
	
	@Test //1.계좌 개설 & 잔액=0
	public void open_new_account() {
		double currentBal = account.getBalance(); //현재 잔액을 가져오고
		assertEquals(0, currentBal, 0.000001); //현재 잔액이 0이 맞는지 확인
	}
	
	@Test //2.예금
	public void deposit_money() {
		Account account = new Account();
		account.deposit_money(100.12); //100.12만큼의 돈을 넣고
		assertEquals(100.12, account.getBalance(), 0.000001); //잔액 확인
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void you_cant_deposit_minus() {
		Account account = new Account();
		account.deposit_money(-15);
	}
}


