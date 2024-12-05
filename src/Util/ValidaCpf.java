/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 *
 * @author chs
 */
public class ValidaCpf {

    public static boolean validaCpf(String strCpf) {
        strCpf = strCpf.replace('.', ' ');
        strCpf = strCpf.replace('-', ' ');
        strCpf = strCpf.replaceAll(" ", "");
        if (!strCpf.substring(0, 1).equals("")) {
            try {
                int d1, d2;
                int digito1, digito2, resto;
                int digitoCPF;
                String nDigResult;
                strCpf = strCpf.replace('.', ' ');
                strCpf = strCpf.replace('-', ' ');
                strCpf = strCpf.replaceAll(" ", "");
                d1 = d2 = 0;
                digito1 = digito2 = resto = 0;

                if (strCpf.equals("00000000000") || strCpf.equals("11111111111") || strCpf.equals("22222222222") || strCpf.equals("33333333333") || strCpf.equals("44444444444") || strCpf.equals("55555555555") || strCpf.equals("66666666666") || strCpf.equals("77777777777") || strCpf.equals("88888888888") || strCpf.equals("99999999999")) {
                } else {
                    for (int nCount = 1; nCount < strCpf.length() - 1; nCount++) {
                        digitoCPF = Integer.valueOf(strCpf.substring(nCount - 1, nCount)).intValue();

                   
                        d1 = d1 + (11 - nCount) * digitoCPF;

                      
                        d2 = d2 + (12 - nCount) * digitoCPF;
                    }
                }


            
                resto = (d1 % 11);

             
                if (resto < 2) {
                    digito1 = 0;
                } else {
                    digito1 = 11 - resto;
                }

                d2 += 2 * digito1;

              
                resto = (d2 % 11);

              
                if (resto < 2) {
                    digito2 = 0;
                } else {
                    digito2 = 11 - resto;
                }

               
                String nDigVerific = strCpf.substring(strCpf.length() - 2, strCpf.length());

              
                nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

            
                return nDigVerific.equals(nDigResult);


            } catch (Exception e) {
                System.err.println("Erro !" + e);
                return false;
            }
        } else {
            return true;
        }
    }
}
