package 구현;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. String.join(구분자, arr) : 배열을 문자열로 합치기
 * 2. str.repeat(반복할 str, 몇번 반복할지) : str이 반복되는 String 만들기
 */
public class BJ16506_CPU {

	static int code;
	static String[] codes;
	static Map<String, String> map = new HashMap<>();
	public static void main(String[] args) throws IOException {
		map.put("ADD","00000");	map.put("ADDC","00001");
		map.put("SUB","00010");	map.put("SUBC","00011");
		map.put("MOV","00100");	map.put("MOVC","00101");
		map.put("AND","00110");	map.put("ANDC","00111");
		map.put("OR","01000");	map.put("ORC","01001");
		map.put("NOT","01010");
		map.put("MULT","01100");	map.put("MULTC","01101");
		map.put("LSFTL","01110"); map.put("LSFTLC","01111");
		map.put("LSFTR","10000"); map.put("LSFTRC","10001");
		map.put("ASFTR","10010"); map.put("ASFTRC","10011");
		map.put("RL","10100");	map.put("RLC","10101");
		map.put("RR","10110");	map.put("RRC","10111");

		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		code = Integer.parseInt(br.readLine());
		codes = new String[code];
		for (int i = 0; i < code; i++) {
			codes[i] = br.readLine();
		}

		for (String code : codes) {
			String[] machineCode = new String[5];

			String[] split = code.split(" ");
			String order = split[0];
			String rD = split[1];
			String rA = split[2];
			String rBorC = split[3];

			machineCode[0] = orderIntoStr(order);	// machineCode[0]
			machineCode[1] = "0";
			machineCode[2] = threeBitBinary(rD);	// machineCode[2]
			machineCode[3] = threeBitBinary(rA);	// machineCode[3]
			machineCode[4] = rBorCIntoStr(rBorC, machineCode[0].substring(4));    // machineCode[4]
			out.println(String.join("", machineCode));
		}

	}

	private static String orderIntoStr(String order) {
		return map.get(order);
	}

	private static String threeBitBinary(String str) {
		StringBuilder sb = new StringBuilder("");
		String binary = Integer.toBinaryString(Integer.parseInt(str));
		int zero = 3 - binary.length();
		sb.append("0".repeat(zero));
		sb.append(binary);
		return sb.toString();
	}

	private static String rBorCIntoStr(String rBorC, String fourthBit) {
		StringBuilder sb = new StringBuilder("");
		if (fourthBit.equals("1")) {
			// 상수
			String cBinary = Integer.toBinaryString(Integer.parseInt(rBorC));
			int zero = 4 - cBinary.length();
			sb.append("0".repeat(zero));
			sb.append(cBinary);
		} else {
			// rB
			sb.append(threeBitBinary(rBorC));
			sb.append("0");
		}
		return sb.toString();
	}
}
