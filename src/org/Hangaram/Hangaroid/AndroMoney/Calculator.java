package org.Hangaram.Hangaroid.AndroMoney;

import java.util.Stack;
import java.util.Vector;

public class Calculator {

	public class OPRTABLE
	{
		public int priority;
		public String opr;
	};
	
	final static int BIN = 0x02;
	final static int OCT = 0x08;
	final static int DEC = 0x0A;
	final static int HEX = 0x10;
	
	char m_cDelimiter;
	int m_nNumOfOpr;
	double m_fResult;
	short m_nNotationFlag;

	int m_nPos = 0;
	boolean m_bDigit = false;
	boolean m_bOp = false;
	
	String m_StringToParse = "";
	Stack<String> m_ParserStack = new Stack<String>();
	Vector<String> m_PostOrderList = new Vector<String>();
	OPRTABLE[] m_pOprTable = null;
	
	
	Calculator()
	{
		m_nNotationFlag = DEC;
		m_nNumOfOpr = 0;
		m_fResult = 0.0;
		m_pOprTable = null;

		SetDelimiter('@'); // 구분자 등록

		//////////////////////////////////
		// 연산자 등록 : 3 letters

		AddOperator(2, "neg"); // 음수
		AddOperator(2, "not"); // not
		AddOperator(3, "sqr"); // 제곱
		AddOperator(3, "sqt"); // 제곱근
		AddOperator(4, "mul"); // 곱하기
		AddOperator(4, "div"); // 나누기
		AddOperator(4, "mod"); // 나머지
		AddOperator(5, "add"); // 더하기
		AddOperator(5, "sub"); // 빼기
	}
	
	public String NextToken() 
	{	
		int nLen = m_StringToParse.length();

		String token = "";

		if(m_nPos < nLen)
		{
			char c = m_StringToParse.charAt(m_nPos++);
			
			if( ( c >= '0' && c <= '9' ) || c == '.' || ( c >= 'A' && c <= 'F' ) ) // 일반 숫자
			{
				token += c;

				m_bDigit = true;

				while(m_bDigit)
				{
					if(m_nPos < nLen)
					{
						c = m_StringToParse.charAt(m_nPos++);
						
						//if((c < '0' && c > '9') && c != '.' && (c < 'A' && c > 'F'))
						if(c == m_cDelimiter)
						{
							m_nPos--;
							m_bDigit = false;
							
							return token;
						}
						
						else token += c;
					}

					else 
					{
						return token;
					}
				}
			}

			else if(c == m_cDelimiter) // 연산자의 시작, 연산자 길이는 3이기 때문에 이후 3글자를 배열에 저장
			{
				token += c;

				for(int i = 0 ; i < 3 ; i++)
					token += m_StringToParse.charAt(m_nPos++);
					
				return token;
			}
		}

		else
			m_nPos = 0;

		return token;
	}

	
	void SetExpression(String strExpression)
	{
		m_StringToParse = strExpression;
		String temp;

		temp = m_StringToParse;

		m_StringToParse = "";

		for(int i = 0 ; i < temp.length() ; i++)
		{
			char c = temp.charAt(i);
			if(c == '+')
				m_StringToParse += "@add";

			else if(c == '-')
			{
				if(i == 0)
					m_StringToParse += "@neg";
				else
					m_StringToParse += "@sub";
			}
			
			
			else if(c == '×')
				m_StringToParse += "@mul";

			else if(c == '÷')
				m_StringToParse += "@div";

			else if(c == '(')
				m_StringToParse += "@opp";

			else if(c == ')')
				m_StringToParse += "@clp";
			
			else if(c == '√')
				m_StringToParse += "@sqt";
			
			else if(c == '^')
				m_StringToParse += "@sqr";
			
			else if(c == ' ')
				continue;
			else
				m_StringToParse += c;
		}

		MakeIntermediate();
		PostOrder();
		m_fResult = Calculate();
	}


	public void AddOperator(int priority, String mnemonic)
	{
		OPRTABLE[] tmp = new OPRTABLE[m_nNumOfOpr + 1];

		for( int i = 0 ; i < tmp.length ; ++i )
			tmp[i] = new OPRTABLE();
		
		if( m_pOprTable != null )
		{
			for( int i = 0 ; i < m_nNumOfOpr ; ++i )
			{
				tmp[i].priority = m_pOprTable[i].priority;
				tmp[i].opr = m_pOprTable[i].opr;
			}
		}

		m_pOprTable = tmp;

		m_pOprTable[m_nNumOfOpr].priority = priority;
		m_pOprTable[m_nNumOfOpr].opr = m_cDelimiter + mnemonic; // 앞에 delimiter를 붙여준다.
		
		m_nNumOfOpr++;

		SortTable(m_nNumOfOpr);
	}



	void SortTable(int nLength)
	{
		for(int i = 0 ; i < nLength - 1 ; i++)
		{
			for(int j = 0 ; j < nLength - i - 1 ; j++)
			{
				if(m_pOprTable[j].priority > m_pOprTable[j+1].priority)
				{
					OPRTABLE tmp = m_pOprTable[j];
					m_pOprTable[j] = m_pOprTable[j+1];
					m_pOprTable[j+1] = tmp;
				}
			}
		}
	}

	void SetDelimiter(char delimiter)
	{
		m_cDelimiter = delimiter;
	}


	/////////////////////////////////////////////////////////////////
	// 이항연산자가 아닐경우 이항 연산식으로 변환 : @not(!), @neg(-)

	void MakeIntermediate()
	{
		String intermediate = "";

		String sub = "";
		String neg = "";
		String not = "";
		String sqt = "";
		
		sub += m_cDelimiter;
		neg += m_cDelimiter;
		not += m_cDelimiter;
		sqt += m_cDelimiter;
		
		sub += "sub";
		neg += "neg";
		not += "not";
		sqt += "sqt";
		
		boolean bContinue = true;

		while(bContinue)
		{
			String token = NextToken();
			bContinue = ( token.length() > 0 );
			
						
			if(token.contains(neg))
			{
				token = NextToken();
				bContinue = ( token.length() > 0 );

				intermediate += m_cDelimiter;
				intermediate += "opp0";
				intermediate += m_cDelimiter;
				intermediate += "sub";
				intermediate += token;
				intermediate += m_cDelimiter;
				intermediate += "clp";
			}
/*			
			else if(token.contains(sub))
			{
				token = NextToken();
				bContinue = ( token.length() > 0 );

				intermediate += m_cDelimiter;
				intermediate += "opp0";
				intermediate += m_cDelimiter;
				intermediate += "sub";
				intermediate += token;
				intermediate += m_cDelimiter;
				intermediate += "clp";
			}
*/		

			else if(token.contains(sqt))
			{
				token = NextToken();
				bContinue = ( token.length() > 0 );

				intermediate += m_cDelimiter;
				intermediate += "opp0";
				intermediate += m_cDelimiter;
				intermediate += "sqt";
				intermediate += token;
				intermediate += m_cDelimiter;
				intermediate += "clp";
			}

			else if(token.contains(not))
			{
				token = NextToken();
				bContinue = ( token.length() > 0 );

				intermediate += m_cDelimiter;
				intermediate += "opp0";
				intermediate += m_cDelimiter;
				intermediate += "sub";
				intermediate += token + m_cDelimiter;
				intermediate += "sub1";
				intermediate += m_cDelimiter;
				intermediate += "clp";
			}

			else
			{
				intermediate += token;
			}
		}

		m_StringToParse = intermediate;
	}



	void PostOrder()
	{
		System.out.println(m_StringToParse);
		
		String token = "";
		String opp = "";
		String clp = "";
		String bottom = "###";

		opp = clp = String.format("%c", m_cDelimiter);

		opp += "opp";
		clp += "clp";

		boolean bParenthesis = false;

		m_ParserStack.clear();
		m_PostOrderList.clear();

		AddOperator(1, "opp");
		AddOperator(9, bottom);

		m_ParserStack.push(m_cDelimiter + bottom);

		boolean bContinue = true;
		
		while(bContinue)
		{
			token = NextToken();
			bContinue = (token.length() > 0);
			
			if( !bContinue )
				continue;
			
			if(token.charAt(0) != m_cDelimiter) // operand일 경우
				m_PostOrderList.add(token);

			else // operator일 경우
			{
				int tokenpri;
				int stackpri;
				
				if(token.contains(opp)) // 괄호가 시작되었을 경우
				{
					bParenthesis = true;
					m_ParserStack.push(token);

					continue;
				}

				else if(token.contains(clp)) // 닫는 괄호일 경우 시작괄호 전까지의 모든 연산자를 결과 스택에 push
				{
					while(!m_ParserStack.peek().contains(opp)) // 시작괄호가 아닐동안 looping
					{
						m_PostOrderList.add(m_ParserStack.peek());
						m_ParserStack.pop();
					}

					m_ParserStack.pop(); // 마지막 시작 괄호 제거

					// 일단 괄호가 모두 제거 됐다고 보고
					bParenthesis = false;
					
					// 스택에 열기 괄호가 존재하는지 검사하여 있으면 다시 괄호 제거가 안 되었다고 설정 
					for( int i = 0 ; i < m_ParserStack.size() ; ++i )
					{
						if( m_ParserStack.elementAt(i).contains( opp ) )
							bParenthesis = true;
					}

					continue;
				}

				tokenpri = GetPriority(token);
				stackpri = GetPriority(m_ParserStack.peek());
				
				if(bParenthesis == false) // 괄호가 없을경우에는 정상 수행
				{
					if(tokenpri < stackpri) // 현재 스택에 있는 연산자 우선순위가 높을경우
						m_ParserStack.push(token); // 그냥 스택에 쌓음
					
					else // 우선순위가 작거나 같을 경우
					{
						m_PostOrderList.add(m_ParserStack.peek()); // 우선 현재 스택의 top값을 결과 저장 스택에 push한 다음
						m_ParserStack.pop(); 
						
						m_ParserStack.push(token); // 우선순위가 낮은 연산자를 push
					}
				}

				else m_ParserStack.push(token); // 여는 괄호가 있을경우 무조건 스택에 쌓음
			}
		}

		while(m_ParserStack.size() > 1) // 아직 스택에 남아있는 나머지 연산자들을 모두 꺼냄
		{
			m_PostOrderList.add(m_ParserStack.peek()); // 우선 현재 스택의 top값을 결과 저장 스택에 push한 다음
			m_ParserStack.pop();
		}

		int i = 0;
		
		String res = "";

		while(i < m_PostOrderList.size())
		{
			res += m_PostOrderList.elementAt(i);
			i++;
		}

		System.out.println(res);
		System.out.println();
		System.out.println(m_PostOrderList.size());
	}

	int GetPriority(String strOpr)
	{
		for(int i = 0 ; i < m_nNumOfOpr ; i++)
		{
			if(strOpr.contentEquals(m_pOprTable[i].opr)) 
				return m_pOprTable[i].priority;
		}

		return -1;
	}


	// 스텍에 저장된 postorder를 계산
	/**
	 * 
	 */
	double Calculate()
	{
		double fResult = 0.0;

		int first;
		int second;
		int i = 0;
		
		try
		{
			while(m_PostOrderList.size() > 1)
			{
				String opr = m_PostOrderList.elementAt(i);
	
				if(opr.charAt(0) == m_cDelimiter)
				{
					second = --i;
					first = --i;
	
					double operand1;
					double operand2;
					
					operand1 = Double.parseDouble(m_PostOrderList.elementAt(first));
					operand2 = Double.parseDouble(m_PostOrderList.elementAt(second));
	
					if(opr.contains("sqr")) m_fResult = Math.pow(operand1, operand2);
					else if(opr.contains("sqt")) fResult = Math.sqrt(operand2);
					else if(opr.contains("mul")) fResult = operand1 * operand2;
					else if(opr.contains("div")) fResult = operand1 / operand2;
					else if(opr.contains("mod")) fResult = (int)operand1 % ((int)operand2);
					else if(opr.contains("add")) fResult = operand1 + operand2;
					else if(opr.contains("sub")) fResult = operand1 - operand2;
	
					m_PostOrderList.setElementAt(String.format("%f", fResult), first);
					m_PostOrderList.removeElementAt(second+1); // operand2를 지운다.
					m_PostOrderList.removeElementAt(first+1); // operator를 지운다.
				}
	
				else i++;
			}
		}
		
		catch( Exception e)
		{
			return fResult = 0.0;
		}
		
		return fResult;
	}

	String GetResult( int Precision )
	{
		String strFormat = "";
		
		int nResult = (int)m_fResult;
		double fFloat = m_fResult - nResult;
		
		if( fFloat == 0 )
			Precision = 0;
		
		if( Precision > 0 )
		{
			strFormat = String.format("%%.%df", Precision);
			return String.format(strFormat, m_fResult);
		}
		else
		{
			strFormat = String.format("%%d");
			return String.format(strFormat, (int)m_fResult);
		}
	}
}
