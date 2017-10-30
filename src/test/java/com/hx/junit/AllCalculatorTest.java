package com.hx.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 大家可以看到，这个功能也需要使用一个特殊的Runner，
 * 因此我们需要向@RunWith标注传递一个参数Suite.class。
 * 同时，我们还需要另外一个标注@Suite.SuiteClasses，
 * 来表明这个类是一个打包测试类。 我们把需要打包的类作为参数传递给该标注就可以了。
 * 有了这两个标注之后，就已经完整的表达了所有的含义，因此下面的类已经无关紧要，
 * 随便起一个类名，内容全部为空既可
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
        {CalculatorTest.class, //
                Calculator2Test.class})
public class AllCalculatorTest {
}
