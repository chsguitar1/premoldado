/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelos;

import java.beans.BeanDescriptor;
import java.beans.SimpleBeanInfo;

/**
 *
 * @author chs
 */
public class TemplateOperacoesBeanInfo extends SimpleBeanInfo {

    private BeanDescriptor beanDescriptor;

    public TemplateOperacoesBeanInfo() {
        super();
    }

    @Override
    public java.beans.BeanDescriptor getBeanDescriptor() {
        if (beanDescriptor == null) {
            beanDescriptor = new BeanDescriptor(TemplateOperacoesBeanInfo.class);

            beanDescriptor.setValue("containerDelegate",
                    "getTemplateOperacoesPane");
        }

        return beanDescriptor;
    }

}

