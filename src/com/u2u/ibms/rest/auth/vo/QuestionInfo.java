package com.u2u.ibms.rest.auth.vo;

public class QuestionInfo {
	//问题
		private String questionName;
		//解答
		private String answer;
		/**
		 * 返回 问题
		 * 
		 * @return 问题
		*/
		public String getQuestionName() {
			return questionName;
		}
		
		/**
		 * 设置 问题
		 * 
		 * @param questionName
		 *            问题
		 */
		public void setQuestionName(String questionName) {
			this.questionName = questionName;
		}
		
		/**
		 * 返回 解答
		 * 
		 * @return 解答
		*/
		public String getAnswer() {
			return answer;
		}
		
		/**
		 * 设置 解答
		 * 
		 * @param answer
		 *            解答
		 */
		public void setAnswer(String answer) {
			this.answer = answer;
		}
		
}
