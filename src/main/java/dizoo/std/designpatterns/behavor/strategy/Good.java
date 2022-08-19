package dizoo.std.designpatterns.behavor.strategy;

public class Good extends Common {

	public Good(String name, Double salary) {
		super(name, salary);
	}

	@Override
	public double bonus(double salary) {
		return salary*0.3;
	}

}
