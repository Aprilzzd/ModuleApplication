package com.example.apt_processor;

import com.example.apt_annotation.BindView;
import com.example.apt_annotation.OnClick;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.CodeBlock;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.*;

@AutoService(Processor.class)
public class BindViewProcessorByPoet extends AbstractProcessor {

    //写入代码会用到
    private Filer filer;
    private Messager mMessager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
        mMessager = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        // 拿到每个类，要生成的代码集合；
        // key为activity类名，list为每个注解生成的相应代码
        Map<TypeElement, List<CodeBlock.Builder>> builderMap = findAndBuilderByTargets(roundEnvironment);
        for (TypeElement typeElement : builderMap.keySet()) {
            mMessager.printMessage(Diagnostic.Kind.NOTE,"--"+typeElement.getSimpleName());
            List<CodeBlock.Builder> codeList = builderMap.get(typeElement);
            // 去生成对应的 类文件；每个activity生成一个额外的类
            BindViewCreatorByPoetHelper.writeBindView(typeElement, codeList, filer);
        }
        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(BindView.class.getCanonicalName());
        types.add(OnClick.class.getCanonicalName());
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    private Map<TypeElement, List<CodeBlock.Builder>> findAndBuilderByTargets(RoundEnvironment env) {
        Map<TypeElement, List<CodeBlock.Builder>> builderMap = new HashMap<>();

        // 遍历带对应注解的元素，就是某个View对象
        for (Element element : env.getElementsAnnotatedWith(BindView.class)) {
            mMessager.printMessage(Diagnostic.Kind.NOTE,"=="+element.getSimpleName());
            //感觉这里面应该是VariableElement
            BindViewCreatorByPoetHelper.parseBindView(element, builderMap);
        }

        // 遍历带对应注解的元素，就是某个方法
        for (Element element : env.getElementsAnnotatedWith(OnClick.class)) {
            BindViewCreatorByPoetHelper.parseListenerView(element, builderMap);
        }
        return builderMap;
    }

}
