/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
// 0, 4, 13, 15 are the talon numbers
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  
  WPI_TalonSRX leftFront = new WPI_TalonSRX(2);
  WPI_TalonSRX leftBack = new WPI_TalonSRX(3);
  WPI_TalonSRX rightFront = new WPI_TalonSRX(0);
  WPI_TalonSRX rightBack = new WPI_TalonSRX(1);

  @Override
  public void initDefaultCommand() {
//    setDefaultCommand(new ArcadeDrive());
  }
  public DriveTrain(){
    initTalons(leftFront, true, false);
    initTalons(leftBack, true, false);
    initTalons(rightFront, false, false);
    initTalons(rightBack, false, false);
    
    SmartDashboard.putBoolean("Left Sensor: ", true);
    SmartDashboard.putBoolean("Left Motor: ", false);
    SmartDashboard.putBoolean("Right Sensor: ", false);
    SmartDashboard.putBoolean("Right Motor: ", false);
  }
  /*
  public void reInitTalons() {
    boolean lSensor = SmartDashboard.getBoolean("Left Sensor: ", true);
    boolean lMotor = SmartDashboard.getBoolean("Left Motor: ", false);
    boolean rSensor = SmartDashboard.getBoolean("Right Sensor: ", false);
    boolean rMotor = SmartDashboard.getBoolean("Right Motor: ", false);
    initTalons(leftFront, lSensor, lMotor);
    initTalons(leftBack, lSensor, lMotor);
    initTalons(rightFront, rSensor, rMotor);
    initTalons(rightBack, rSensor, rMotor);
  }
  */
  public void initTalons(WPI_TalonSRX motor, boolean sensorPhase, boolean invert) {
    motor.configFactoryDefault();
    motor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    motor.setSensorPhase(sensorPhase);
    motor.setInverted(invert);
    motor.setSelectedSensorPosition(0);
  }
  public void setMotor(double x, double y){
    leftFront.set(x);
    leftBack.set(x);
    rightBack.set(y);
    rightFront.set(y);
    SmartDashboard.putNumber("Left Front speed", leftFront.getSelectedSensorVelocity());
  }
  public void manualDrive(){
    leftFront.set(0.1*Robot.oi.getX());
    leftBack.set(0.1*Robot.oi.getX());
    rightFront.set(0.1*Robot.oi.getY());
    rightBack.set(0.1*Robot.oi.getY());
    SmartDashboard.putNumber("Left Front speed", leftFront.getSelectedSensorVelocity());
    SmartDashboard.putNumber("Joystick Value X", Robot.oi.getX());
    SmartDashboard.putNumber("Joystick Value Y", Robot.oi.getY());
  }
}
