package com.oopdev.io.util.validators;




public class IOValueValidator {


/**
 * 	
 * @param field 
 * @param confirmField
 * @return field equal confirmField return true else return false
 */
	public static <O> boolean isEqual(O field,O confirmField){
		if(field==null){
			if(confirmField==null)
				return true;
			else
				return false;
		}
		if(field.equals(confirmField))
			return true;
		else
			return false;
	}
	/**
	 * 	
	 * @param field 
	 * @param confirmField
	 * @return field equal ignore case sensitive confirmField return true else return false
	 */
	public static boolean isIgnoreCaseEqual(String field,String confirmField){
		if(field==null){
			if(confirmField==null)
				return true;
			else
				return false;
		}
		if(field.equalsIgnoreCase(confirmField))
			return true;
		else
			return false;
	}


	/**
	 * 
	 * @param variableName
	 * @return
	 */
	public static boolean isVariableName(String variableName){
		return (variableName!=null&&variableName.matches("[0-9A-Za-z.]([0-9A-Za-z._])+"));
	}
	/**
	 * 
	 * @param path
	 * @return
	 */
	public static boolean isRequestPath(String path){
		return (path!=null&&path.matches("/[0-9A-Za-z.]([0-9A-Za-z._])+/[0-9A-Za-z.]([0-9A-Za-z._])+[.]([A-Za-z])+"));
	}
	
	public static <O> boolean isJavaPrimitive(O o) {
	return (o != null && IOTypeValidator.isJavaPrimitive(o.getClass()));
	}	
	
	public static void main(String[] args) {
		System.out.println(isLongThenReturn(124));
	}
	
	
	public static final <O> boolean isEmpty(O o){
		return (o==null)||(o instanceof String&&o=="");
	}
	
	/**
	 *  JAVA TYPES CONTROL BEGIN
	 *  Byte,Short,Integer,Long,Float,Double
	 */
	
	
	/**
	 * Byte control if is byte return value else return null
	 * @param o
	 * @return
	 */
	public static final <O> Byte isByteThenReturn(O o){
		try{ return new Byte(o.toString());}catch (Exception e) {return null;}
	}
	/**
	 * Byte control if is byte return true else return false
	 * @param o
	 * @return
	 */
	public static final <O> boolean isByte(O o){
		try{ new Byte(o.toString());}catch (Exception e) {return false;}
		 return true ;
	}
	
	
	/**
	 * Short control if is short return value else return null
	 * @param o
	 * @return
	 */
	public static final <O> Short isShortThenReturn(O o){
		try{ return new Short(o.toString());}catch (Exception e) {return null;}
	}
	/**
	 * Short control if is short return true else return false
	 * @param o
	 * @return
	 */
	public static final <O> boolean isShort(O o){
		try{ new Short(o.toString());}catch (Exception e) {return false;}
		return true; 
	}

	/**
	 * Integer control if is Integer return value else return null
	 * @param o
	 * @return
	 */
	public static final <O> Integer isIntegerThenReturn(O o){
		try{return new Integer(o.toString());}catch (Exception e) {return null;}
	}
	/**
	 * Integer control if is Integer return true else return false
	 * @param o
	 * @return
	 */
	public static final <O> boolean isInteger(O o){
		try{ new Integer(o.toString());}catch (Exception e) {return false;}
		return true;
	}
	
	
	/**
	 * Long control if is long return value else return null
	 * @param o
	 * @return
	 */
	public static final <O> Long isLongThenReturn(O o){
		try{return new Long(o.toString());}catch (Exception e) {return null;}
	}
	
	/**
	 * Long control if is long return true else return false
	 * @param o
	 * @return
	 */
	public static final <O> boolean isLong(O o){
		try{ new Long(o.toString());}catch (Exception e) {return false;}
		return true;
	}
	/**
	 * Float control if is float return value else return null
	 * @param o
	 * @return
	 */	
	public static final <O> Float isFloatThenReturn(O o){
		try{return new Float(o.toString());}catch (Exception e) {return null;}
	}
	/**
	 * Float control if is float return true else return false
	 * @param o
	 * @return
	 */	
	public static final <O> boolean isFloat(O o){
		try{new Float(o.toString());}catch (Exception e) {return false;}
		return true;
	}
	/**
	 * Double control if is double return value else return null
	 * @param o
	 * @return
	 */
	public static final <O> Double isDoubleThenReturn(O o){
		try{return new Double(o.toString());}catch (Exception e) {return null;}
	}
	/**
	 * Double control if is double return true else return false
	 * @param o
	 * @return
	 */
	public static final <O> boolean isDouble(O o){
		try{ new Double(o.toString());}catch (Exception e) {return false;}
		return true;
	}
	/**
	 * Boolean control if is boolean return value else return null
	 * @param o
	 * @return
	 */
	public static final <O> Boolean isBooleanThenReturn(O o){
		try{return new Boolean(o.toString());}catch (Exception e) {return null;}
	}
	/**
	 * Boolean control if is boolean return value else return null
	 * @param o
	 * @return
	 */
	public static final <O> boolean isBoolean(O o){
		try{ new Boolean(o.toString());}catch (Exception e) {return false;}
		return true;
	}
	/**
	 * Boolean control if is boolean return value else return null
	 * @param o
	 * @return
	 */
	public static final <O> Character isCharThenReturn(O o){
		try{ return o.toString().length()==1&&isJavaPrimitive(o)?o.toString().charAt(0):null; }catch (Exception e) {return null;}
	}
	/**
	 * Boolean control if is boolean return value else return null
	 * @param o
	 * @return
	 */
	public static final <O> boolean isChar(O o){
		try{ return (o.toString().length()==1&&isJavaPrimitive(o)?o.toString().charAt(0):null)==null?true:false; }catch (Exception e) {return false;}

	}
}