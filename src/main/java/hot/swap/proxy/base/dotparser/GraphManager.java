package hot.swap.proxy.base.dotparser;

import antlr.TokenBuffer;
import antlr.TokenStream;
import antlr.collections.AST;
import hot.swap.proxy.base.dotgenerate.DotLexer;
import hot.swap.proxy.base.dotgenerate.DotParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by leeshine on 3/21/17.
 */

public class GraphManager {
    private static final int GRAPH_ARGUMENT = 1;
    private static final int NODE = 2;
    private static final int EDGE = 3;

    private InputStream inputStream;
    private Graph graph;

    public GraphManager(String inputFile) throws FileNotFoundException{
         inputStream = new FileInputStream(inputFile);
        graph = new Graph();
    }

    public Graph parserGraph() throws Exception{
        AST ast = buildAST();

        graph.setGraph_name(getASTName(ast));

        AST cur_ast = ast.getFirstChild();
        int type = -1;

        while(cur_ast != null){
            cur_ast = cur_ast.getNextSibling();
            type = getType(cur_ast);

            switch (type){
                case GRAPH_ARGUMENT:
                    addGraphArgument(cur_ast);
                    break;
                case NODE:
                    addNode(cur_ast);
                    break;
                case EDGE:
                    addEdge(cur_ast);
                    break;
                default:
                    break;
            }
        }

        return graph;
    }

    private AST buildAST() throws Exception{
        TokenStream dot_lexer = new DotLexer(inputStream);
        TokenBuffer token_buffer = new TokenBuffer(dot_lexer);
        DotParser dot_parser = new DotParser(token_buffer);

        dot_parser.graph();

        return dot_parser.getAST();
    }

    public String getASTName(final AST ast) throws Exception{
        String result = null;
        AST name = ast.getFirstChild();

        if(name != null){
            result = name.getText();
        }

        return result;
    }

    public int getType(final AST ast){
        int type = -1;
        AST cur_ast = ast;
        if(cur_ast != null)
            cur_ast = ast.getFirstChild();

        if(cur_ast != null){
            String value = cur_ast.getText();
            if(cur_ast.getNextSibling()!=null && ("->".equals(value))) {
                type = EDGE;
            }else{
                if(cur_ast.getFirstChild() != null){
                    type = NODE;
                }else{
                    type = GRAPH_ARGUMENT;
                }
            }
        }

        return type;
    }

    public void addGraphArgument(final AST ast) throws Exception{
        AST cur_ast = ast;

        AST child_ast = null;
        String name;
        String value;
        int type = getType(ast);

        while(type == GRAPH_ARGUMENT){
            name = cur_ast.getText();
            child_ast = cur_ast.getFirstChild();

            if(child_ast!=null && child_ast.getFirstChild()==null
                    && child_ast.getNextSibling()==null){
                value = child_ast.getText();
                graph.addValue(name,value);
            }

            cur_ast = cur_ast.getNextSibling();
            type = getType(cur_ast);
        }
    }

    /*public void addArgument(final AST ast, final ArgumentBean) throws Exception{
    }*/

    public void addNode(final AST ast) throws Exception{
        String node_name = ast.getText();
        Node node = graph.getAndaddNode(node_name);//add node to graph

        AST cur_ast = ast.getFirstChild();
        while(cur_ast != null){
            String name = cur_ast.getText();
            String value = getASTName(cur_ast);
            if(name!=null && value!=null)
                node.addValue(name,value);
            cur_ast = cur_ast.getNextSibling();
        }
    }

    public void addEdge(final AST ast) throws Exception{
        String head = getHeadNode(ast);
        String tail = getTailNode(ast);

        if(head==null || tail==null)
            throw new Exception("init node error!");

        Edge edge = new Edge(head,tail);

        AST cur_ast = ast.getFirstChild();
        while(cur_ast != null){
            edge.addValue(cur_ast.getText(),getASTName(cur_ast));
            cur_ast = cur_ast.getNextSibling();
        }

        graph.addEdge(edge);
    }

    public String getHeadNode(final AST ast) throws Exception{
        /*Node result = null;
        String node_name = ast.getText();

        result = graph.getNode(node_name);
        if(result == null)
            result = new Node(node_name);

        return result;*/
        return ast.getText();
    }

    public String getTailNode(final AST ast) throws Exception{
       /* Node result = null;
        AST cur_ast = ast.getFirstChild();
        if(cur_ast != null)
            cur_ast = cur_ast.getNextSibling();

        if(cur_ast != null){
            String node_name = cur_ast.getText();

            result = graph.getNode(node_name);
            if(result == null)
                result = new Node(node_name);
        }

        return result;*/
        String result = null;
        AST cur_ast = ast.getFirstChild();
        if(cur_ast != null)
            cur_ast = cur_ast.getNextSibling();

        if(cur_ast != null)
            result = cur_ast.getText();

        return result;
    }
}
