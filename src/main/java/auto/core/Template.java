/*
 * 文件名：Template.java
 * 版权：Copyright by www.cfilmcloud.com
 * 描述：
 * 修改人：mengxy[孟祥元]
 * 修改时间：2016年4月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package auto.core;

import cn.org.rapid_framework.generator.GeneratorFacade;

public class Template {

    public static void main(String[] args) throws Exception {
        String templatePath = "/Users/mac/Documents/my/support/src/main/resources/template";
        GeneratorFacade g = new GeneratorFacade();
        g.getGenerator().addTemplateRootDir(templatePath);
        //删除生成器的输出目录//
        g.deleteOutRootDir();
        //通过数据库表生成文件
        g.generateByTable("t_basic_rule", "t_basic_rule_approval");
    }

}
