package com.hx.designpatterns.behavor.state;

/**
 * Created by hx on 16-9-19.
 */
public class Context {
  private State state;

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public void operation(String str){
    if (str.equals("1")) {state=new ConcreteState1();}
    else state=new ConcreteState2();
    state.operation();
  }

  public static void main(String[] args) {
    Context context = new Context();
    context.operation("1");
  }

}
