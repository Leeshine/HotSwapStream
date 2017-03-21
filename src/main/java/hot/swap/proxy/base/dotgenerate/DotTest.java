package hot.swap.proxy.base.dotgenerate;

import antlr.TokenBuffer;
import antlr.collections.AST;

import java.io.StringBufferInputStream;

/**
 * Created by leeshine on 3/21/17.
 */
public class DotTest {
    public static final String TEST_INPUT_1 =
            "digraph nlpMessagingGenerator {\n"
                    + "    overlap=scale;\n"
                    + "    splines=true;\n"
                    + "    layers=\"process:errors\";\n"
                    + "    rankdir=TD;\n"
                    + "    concentrate=true;\n"
                    + "    node [shape=box, style=\"rounded,filled\", layer=all, fillcolor=\"darkslategray3\"];\n"
                    + "    edge [layer=all];\n"
                    + "    start [label=\"\", shape=circle, fillcolor=black, fixedsize=true, width=0.2];\n\n"
                    + "    readXml [label=\"Read XML\"];\n"
                    + "    retrieveNextCommType [label=\"Retrieve next\nCommunication Type\"];\n"
                    + "    checkCommType[label=\"Check whether\nCommunication Type\nexists\", fillcolor=\"orange\", shape=\"diamond\"];\n"
                    + "    // beginTransaction[label=\"Begin transaction\", fillcolor=\"#D2D263\"];\n"
                    + "    createCommType[label=\"Create Communication Type\"];\n"
                    + "    createTemplate[label=\"Create Template\"];\n"
                    + "    // commitTransaction[label=\"Commit transaction\", fillcolor=\"#D2D263\"];\n"
                    + "    // rollbackTransaction[label=\"Rollback transaction\", fillcolor=\"#D2D263\"];\n"
                    + "    error [label=\"Error\", layer=\"error\", fillcolor=\"red\"];\n"
                    + "    end [label=\"\", shape=doublecircle, fillcolor=\"black\", fixedsize=true, width=0.2];\n"
                    + "    start -> readXml [label=\"0\"];\n"
                    + "    readXml -> retrieveNextCommType [label=\"0\"];\n"
                    + "    readXml -> error [label=\"-1\", layer=\"error\", color=\"gray85\", fontcolor=\"gray85\"];\n"
                    + "    retrieveNextCommType -> checkCommType [label=\"0\", decorate=\"true\"];\n"
                    + "    retrieveNextCommType -> end[label=\"1\", color=\"darkgoldenrod4\", fontcolor=\"darkgoldenrod4\"];\n"
                    + "    retrieveNextCommType -> error [label=\"-1\", layer=\"error\", color=\"gray85\", fontcolor=\"gray85\"];\n"
                    + "    checkCommType -> retrieveNextCommType[label=\"0\"];\n"
                    + "    // checkCommType -> beginTransaction[label=\"1\", color=\"darkgoldenrod4\", fontcolor=\"darkgoldenrod4\"];\n"
                    + "    checkCommType -> createCommType[label=\"1\", color=\"darkgoldenrod4\", fontcolor=\"darkgoldenrod4\"];\n"
                    + "    checkCommType -> error[label=\"-1\", layer=\"error\", color=\"gray85\", fontcolor=\"gray85\"];\n"
                    + "    // beginTransaction -> createCommType[label=\"1\", color=\"darkgoldenrod4\", fontcolor=\"darkgoldenrod4\"];\n"
                    + "    // beginTransaction -> error[label=\"-1\", layer=\"error\", color=\"gray85\", fontcolor=\"gray85\"];\n"
                    + "    createCommType -> createTemplate [label=\"0\"];\n"
                    + "    // createCommType -> rollbackTransaction [label=\"-1\", layer=\"error\", color=\"gray85\", fontcolor=\"gray85\"];\n"
                    + "    createCommType -> error [label=\"-1\", layer=\"error\", color=\"gray85\", fontcolor=\"gray85\"];\n"
                    + "    // createTemplate -> commitTransaction [label=\"0\"];\n"
                    + "    createTemplate -> retrieveNextCommType [label=\"0\"];\n"
                    + "    // createTemplate -> rollbackTransaction [label=\"-1\", layer=\"error\", color=\"gray85\", fontcolor=\"gray85\"];\n"
                    + "    createTemplate -> error [label=\"-1\", layer=\"error\", color=\"gray85\", fontcolor=\"gray85\"];\n"
                    + "    // commitTransaction -> retrieveNextCommType [label=\"0\"];\n"
                    + "    // commitTransaction -> rollbackTransaction [label=\"-1\", layer=\"error\", color=\"gray85\", fontcolor=\"gray85\"];\n"
                    + "    // rollbackTransaction -> error [label=\"0\"];\n"
                    + "    // rollbackTransaction -> error [label=\"-1\", layer=\"error\", color=\"gray85\", fontcolor=\"gray85\"];\n"
                    + "    error -> end[label=\"0\", layer=\"error\", color=\"gray85\", fontcolor=\"gray85\", fontcolor=\"gray85\", fontcolor=\"gray85\"];\n"
                    + "}\n";

    public static void main(String[] args) throws Exception{
        DotLexer t_DotLexer =
                new DotLexer(
                        new StringBufferInputStream(TEST_INPUT_1));


        TokenBuffer t_TokenBuffer = new TokenBuffer(t_DotLexer);
        DotParser t_DotParser = new DotParser(t_TokenBuffer);

        t_DotParser.graph();
        AST result =  t_DotParser.getAST();
        System.out.println(result.getFirstChild().getText());
    }
}
