
package encoder;

public class Encoder {
    public static void main(String[] args) {
       // System.out.println(refTable());
      //  System.out.println(offsetValue('B', refTable()));
       // System.out.println(shiftTable(offsetValue('B', refTable()),refTable()));
       char[] a = plainTextChar("HELLO WORLD");
        System.out.println("Input String");
        System.out.println(a);
        char[] refTable1 = refTable();
        int offsetValue1 = offsetValue('B',refTable1);
     //   System.out.println(offsetValue1);shift by 5 steps if F
      //  System.out.println(shiftTable(offsetValue1, refTable1)); new shift table
       char[] b = shiftTable(offsetValue1,refTable1);
       String encoded = encoded(plainTextIndex(a,refTable1), b);
        System.out.println("Output String");
        System.out.println('B' + encoded);
        
        decoded(encoded);

    }
            
public static char[] refTable(){
char[] refTable= new char[45];
for( int i=0; i<45; i++){
    if(i<26){
        int asciiValue = i +65;
        refTable[i]= (char)asciiValue;
    }
    else if(i>=26 && i<36){
        int asciiValue = i+22;
        refTable[i] = (char)asciiValue;
    }
    else if( i==44){
        refTable[i]= (char)32;
    }
    else{
        int asciiValue = i+4;
        refTable[i] = (char)asciiValue;
    }    
}
return refTable;
}

public static int offsetValue(char letter, char[] refTable){
    for (int i=0; i<44;i++){
    if(refTable[i]==letter){
        return i;
    }
    }
    return -1;
}
    
public static char[] shiftTable(int offsetValue, char[] refTable){
    char[] shiftTable = new char[45];
    
    for(int i=0; i<45; i++){
        if((i+offsetValue)>43)  {  
            if(i==44){
                       shiftTable[i]=' ';
            }else{
               shiftTable[i+offsetValue-44]=refTable[i]; 
            }
        }
            else
        {
          shiftTable[i+offsetValue]=refTable[i];  
        }
    }
    return shiftTable;
} 
    
public static char[] plainTextChar(String plainText){
    char[] plainTextChar = plainText.toCharArray();
    return plainTextChar;
}
public static int[] plainTextIndex(char[] plainTextChar,char[] refTable){
    int[] plainTextInt = new int[plainTextChar.length];
    for( int i=0; i< plainTextChar.length; i++){
        if(plainTextChar[i]==(char)32){
            plainTextInt[i]=44;
            continue;
        }
        for(int j=0; j<45;j++){
            if(plainTextChar[i]==refTable[j])
                plainTextInt[i] = j;
        }
    }
       return plainTextInt;
    }

public static String encoded(int[] plainTextInt, char[] offsetTable){
    char[] encodedChar = new char[plainTextInt.length];
    for( int i=0; i< plainTextInt.length; i++){
        encodedChar[i] = offsetTable[plainTextInt[i]];
        }
   return new String(encodedChar);

}

public static void decoded(String encoded){
    char[] encodedCharArr = plainTextChar(encoded);
    char[] refTable1 = refTable();
    int offsetValue1 = offsetValue('B',refTable1);
    char[] b = shiftTable(offsetValue1,refTable1);
    String decoded = encoded(plainTextIndex(encodedCharArr,b), refTable1);
    System.out.println("Decoded string: ");
    System.out.println(decoded);
    
}

}


