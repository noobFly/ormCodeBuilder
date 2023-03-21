package cn.org.rapid_framework.generator;


/**
 * 
 * @author badqiu
 * @email badqiu(a)gmail.com
 */
public class GeneratorMain {

	public static void main(String[] args) throws Exception {
		GeneratorFacade g = new GeneratorFacade();
//		g.printAllTableNames();				//打印全部表名
		
		g.deleteOutRootDir();							//删除输出目录
		g.generateByTable("data_source_config","template_smp");	//单表生成及使用的模板，使用*表示所有表，指定多个表可以用逗号隔开多个表名
//		g.generateByAllTable("template_sm");	//使用模板生成所有表
//		g.generateByClass(Blog.class,"template_clazz");
		
//		g.deleteByTable("table_name", "template"); //删除
		Runtime.getRuntime().exec("cmd.exe /c start "+GeneratorProperties.getRequiredProperty("outRoot")); //自动打开生成目录，只有window下才能执行，其它系统注释掉
	}
}
