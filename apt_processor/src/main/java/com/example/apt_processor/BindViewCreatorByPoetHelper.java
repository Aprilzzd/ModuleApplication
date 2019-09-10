package com.example.apt_processor;

import com.example.apt_annotation.BindView;
import com.example.apt_annotation.OnClick;
import com.squareup.javapoet.*;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BindViewCreatorByPoetHelper {

    public static void parseBindView(Element element, Map<TypeElement, List<CodeBlock.Builder>> codeBuilderMap) {
        //获取最外层的类名，具体实际就是关联某个Activity对象Element
        //因为此时的element是VriableElement,所以拿到的Enclosing 就应该是Activity对象
        TypeElement classElement = (TypeElement) element.getEnclosingElement();
        // 这个view是哪个类 Class(android.widget.TextView)
        String viewType = element.asType().toString();
        // 注解的值，具体实际可能就是 R.id.xxx
        int value = element.getAnnotation(BindView.class).value();
        // 这个view对象名称（比如TextView）
        String name = element.getSimpleName().toString();

        //创建代码块,findViewById
        //$L是占位符，会把后面的 name 参数拼接到 $L 所在的地方
        CodeBlock.Builder builder = CodeBlock.builder()
                .add("target.$L = ", name);
        builder.add("($L)view.findViewById($L)", viewType, value);

        List<CodeBlock.Builder> codeList = codeBuilderMap.get(classElement);
        if (codeList == null) {
            codeList = new ArrayList<>();
            codeBuilderMap.put(classElement, codeList);
        }
        codeList.add(builder);
    }

    public static void parseListenerView(Element element, Map<TypeElement, List<CodeBlock.Builder>> codeBuilderMap) {
        //获取最外层的类名，具体实际就是关联某个Activity对象Element
        TypeElement classElement = (TypeElement) element.getEnclosingElement();

        List<CodeBlock.Builder> codeList = codeBuilderMap.get(classElement);
        if (codeList == null) {
            codeList = new ArrayList<>();
            codeBuilderMap.put(classElement, codeList);
        }

        //注解的值
        int[] annotationValue = element.getAnnotation(OnClick.class).value();

        //因为注解@Target是Method，所以这面拿到的就是方法名字的字符串
        String name = element.getSimpleName().toString();

        //创建代码块
        for (int value : annotationValue) {
            CodeBlock.Builder builder = CodeBlock.builder();
            builder.add("view.findViewById($L).setOnClickListener(new android.view.View.OnClickListener() { public void onClick(View v) { target.$L(v); }})", value, name);
            codeList.add(builder);
        }
    }

    public static void writeBindView(TypeElement classElement, List<CodeBlock.Builder> codeList, Filer filer) {
        // enclosingElement ，暗指 某个Activity.
        // 先拿到 Activity 所在包名( cn.citytag.aptdemo.Main3Activity)
        String packageName = classElement.getQualifiedName().toString();
        packageName = packageName.substring(0, packageName.lastIndexOf("."));//(cn.citytag.aptdemo)
        // 再拿到Activity类名(Main3Activity))
        String className = classElement.getSimpleName().toString();

        //此元素定义的类型
        TypeName type = TypeName.get(classElement.asType());

        //if (type instanceof ParameterizedTypeName) {
        // type = ((ParameterizedTypeName) type).rawType;
        //}

        // 创建类 MainActivity_ViewBinding的构造器
        ClassName bindingClassName = ClassName.get(packageName, className + "_ViewBindingPoet");
        MethodSpec.Builder methodSpecBuilder = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(type, "target", Modifier.FINAL)
                .addParameter(ClassName.get("android.view", "View"), "view", Modifier.FINAL);
        for (CodeBlock.Builder codeBuilder : codeList) {
            //方法里面 ，代码是什么
            methodSpecBuilder.addStatement(codeBuilder.build());
        }
        methodSpecBuilder.build();

        // 创建类 MainActivity_ViewBinding
        TypeSpec bindClass = TypeSpec.classBuilder(bindingClassName.simpleName())
                .addModifiers(Modifier.PUBLIC)
                .addMethod(methodSpecBuilder.build())
                .build();

        try {
            // 生成文件
            JavaFile javaFile = JavaFile.builder(packageName, bindClass).build();
            //将文件写出
            javaFile.writeTo(filer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
