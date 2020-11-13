public class Car {
  private String make;
  private String model;
  private int year;
  private int mileage;

  public Car() { make = ""; model = ""; year = 0; mileage = 0; }
  
  //Four-argument constructor
  public Car(String make, String model, int year, int mileage) {
      this.make = make; 
      this.model = model; 
      this.year = year;
      this.mileage = mileage;   
  }

  //Set methods for instance variables
  public void setMake(String s) { make = s; }
  public void setModel(String s) { model = s; }
  public void setYear(int i) { year = i; }
  public void setMileage(int i) { mileage = i; }
  
  //Get methods for instance variables
  public String getMake() { return make; }
  public String getModel() { return model; }
  public int getYear() { return year; }
  public int getMileage() { return mileage; }

  public String toString() {
    return String.format("%s,%s,%d,%d", make, model, year, mileage);
  }
}