package com.my.leetcode.parse_lisp_expr;

import java.util.*;

public class Solution {

    public enum Type {
        ADD,
        MULT,
        LET
    }

    public static void main(String[] args) {
        System.out.println(new Solution().evaluate("(let x 1 x (add x 1) (add x 1))"));
        //
        System.out.println(new Solution().evaluate("(let x 1 y 2 x (add x y) (add x y))"));
        //5
        /*System.out.println(new Solution().evaluate("(let x 2 (add (let x 3 x) x))"));
        //5
        System.out.println(new Solution().evaluate("(let x 3 x 2 x)"));
        //2
        System.out.println(new Solution().evaluate("(add 1 2)"));
        //3
        System.out.println(new Solution().evaluate("(mult 3 (add 2 3))"));
        //15
        System.out.println(new Solution().evaluate("(let x 2 (mult x 5))"));
        //10
        System.out.println(new Solution().evaluate("(let x 2 (mult x (let x 3 y 4 (add x y))))"));
        //14
        System.out.println(new Solution().evaluate("(let x 2 (add (let x 3 (let x 4 x)) x))"));
        //6
        System.out.println(new Solution().evaluate("(let a1 3 b2 (add a1 1) b2)"));
        //4
        System.out.println(new Solution().evaluate("(let x 7 -12)"));
        //-12*/
    }

    public int evaluate(String expression) {
        Scope scope = new Scope();
        return parseExpr(expression).evaluate(scope);
    }

    private Expr parseExpr(String expression) {
        if (expression.startsWith("(")) {
            expression = expression.substring(1, expression.length() - 1);
        }

        if (expression.charAt(0) == '-' || (expression.charAt(0) >= '0' && expression.charAt(0) <= '9')) {
            return new Value(Integer.parseInt(expression));
        }

        if (expression.contains(" ")) {
            String operation = expression.substring(0, expression.indexOf(" "));
            expression = expression.substring(expression.indexOf(" ") + 1);
            List<Expr> exprList = parseListOfExpressions(expression);
            switch (operation) {
                case "add": {
                    return new Add(exprList.get(0), exprList.get(1));
                }
                case "mult": {
                    return new Mult(exprList.get(0), exprList.get(1));
                }
                case "let": {
                    Queue<Assign> assigns = new LinkedList<>();
                    for (int i = 0; i < exprList.size() - 1; i += 2) {
                        assigns.offer(new Assign((Variable) exprList.get(i), exprList.get(i + 1)));
                    }
                    return new Lit(assigns, exprList.get(exprList.size() - 1));
                }
                default: {
                    throw new RuntimeException("Parse exception: \"" + expression + "\"");
                }
            }
        } else {
            return new Variable(expression);
        }
    }

    private List<Expr> parseListOfExpressions(String expression) {
        List<Expr> exprList = new ArrayList<>();
        while (!expression.isEmpty()) {
            if (expression.startsWith("(")) {
                int lastIndex = findEndOfExpression(expression);
                exprList.add(parseExpr(expression.substring(0, lastIndex + 1)));
                expression = expression.substring(Math.min(lastIndex + 2, expression.length()));
            } else {
                exprList.add(parseExpr(expression.substring(0,
                        expression.contains(" ")? expression.indexOf(" "): expression.length())));
                expression = expression.substring(expression.contains(" ")? expression.indexOf(" ") + 1:
                        expression.length());
            }
        }
        return exprList;
    }

    private int findEndOfExpression(String expression) {
        int open = 1;
        for (int i = 1; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') open++;
            else if (expression.charAt(i) == ')') open--;
            if (open == 0) {
                return i;
            }
        }
        throw new RuntimeException("Parse exception: \"" + expression + "\"");
    }

    private interface Expr {
        int evaluate(Scope scope);
    }

    private static class Value implements Expr {

        private int value;

        public Value(int value) {
            this.value = value;
        }

        @Override
        public int evaluate(Scope scope) {
            return value;
        }
    }

    private static class Variable implements Expr {

        private String name;

        public Variable(String name) {
            this.name = name;
        }

        @Override
        public int evaluate(Scope scope) {
            scope = scope.createInnerScopeFor(this);
            return scope.get(this).evaluate(scope);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            Variable variable = (Variable) o;
            return Objects.equals(name, variable.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    private static class AssignVariable extends Variable {

        public AssignVariable(String name) {
            super(name);
        }

        @Override
        public int evaluate(Scope scope) {
            if (scope.get(this) instanceof Value) {
                return scope.get(this).evaluate(scope.createInnerScope());
            } else {
                scope = scope.createInnerScopeFor(this);
                return scope.get(this).evaluate(scope);
            }
        }
    }

    private static class Add implements Expr {
        private Expr left;
        private Expr right;

        public Add(Expr left, Expr right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int evaluate(Scope scope) {
            scope = scope.createInnerScope();
            return left.evaluate(scope) + right.evaluate(scope);
        }
    }

    private static class Mult implements Expr {

        private Expr left;
        private Expr right;

        public Mult(Expr left, Expr right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int evaluate(Scope scope) {
            scope = scope.createInnerScope();
            return left.evaluate(scope) * right.evaluate(scope);
        }
    }

    private static class Lit implements Expr {

        //stack
        private Queue<Assign> assigns;

        private Expr expr;

        public Lit(Queue<Assign> assigns, Expr expr) {
            this.assigns = assigns;
            this.expr = expr;
        }

        @Override
        public int evaluate(Scope scope) {
            Scope innerScope = new Scope();
            while (!assigns.isEmpty()) {
                assigns.poll().assign(innerScope);
            }
            innerScope.mergeWithOuter(scope);
            return expr.evaluate(innerScope);
        }
    }

    private static class Assign {

        private Variable variable;
        private Expr expr;

        public Assign(Variable variable, Expr expr) {
            this.variable = variable;
            this.expr = expr;
        }

        public void assign(Scope scope) {
            //TODO
            //scope.put(variable, new Value(expr.evaluate(scope)));
            scope.put(new AssignVariable(variable.name), expr);
        }
    }

    private static class Scope {

        private Map<Variable, Deque<Expr>> scope = new HashMap<>();

        public Expr get(Variable key) {
            return scope.get(key).peekLast();
        }

        public void put(Variable key, Expr value) {
            scope.putIfAbsent(key, new LinkedList<>());
            scope.get(key).offer(value);
        }

        public Scope createInnerScope() {
            Scope innerScope = new Scope();
            innerScope.scope.putAll(scope);
            return innerScope;
        }

        public Scope createInnerScopeFor(Variable variable) {
            Scope innerScope = new Scope();
            innerScope.scope.putAll(scope);
            innerScope.scope.get(variable).pollLast();
            return innerScope;
        }

        public void mergeWithOuter(Scope outerScope) {
            for (Map.Entry<Variable, Deque<Expr>> entry : outerScope.scope.entrySet()) {
                if (!scope.containsKey(entry.getKey())) {
                    scope.put(entry.getKey(), entry.getValue());
                }
            }
        }
    }
}
