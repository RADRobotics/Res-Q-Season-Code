package com.qualcomm.ftcrobotcontroller.opmodes.Green.Griffins;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * This op mode was generated by FTC-autoDrawer.
 * Code generated from file C:\Users\David\Desktop\FTC Projects\ftc_app\stuff\FtcAutoDrawerTest, 
 * on Thu Jan 21 19:02:32 PST 2016,
 * by David.
 * 
 * Insert your own documentation here
 */


public class FtcAutoDrawerTest extends LinearOpMode { 

    final int ENCODER_COUNTS_PER_ROTATION = 1440; // 1440 for tetrix motor encoders, 1120 for andymark neverest 40 encoders
    final double INCHES_PER_ROTATION =  Math.PI * 4.0 * 1.0; // pi times wheel diameter (circumference) times gear ratio
    double driveSpeed = 1; //must be between 0 and 1, this is the speed the motors will drive at

    //Create motor variables
    RobotHardware hardware = new RobotHardware(hardwareMap);

    @Override
    public void runOpMode() throws InterruptedException {

        //Wait for the start button to be pressed
        waitForStart();

        //Drive commands
        autoDrive(21.570982331590937);

        autoTurn(-49.40584991253852);
        autoDrive(32.56093122255295);

        autoTurn(-75.05598876137975);
        autoDrive(12.717990009855942);

        autoTurn(-9.344671902099696);
        autoDrive(36.5356889124781);
        //extend arm here
    }

    private void autoDrive(double inches) throws InterruptedException{
        int encoderCounts = (int)(inches/INCHES_PER_ROTATION*ENCODER_COUNTS_PER_ROTATION);
        //reset encoders
        hardware.getRightDriveMotor().setMode(DcMotorController.RunMode.RESET_ENCODERS);
        hardware.getLeftDriveMotor().setMode(DcMotorController.RunMode.RESET_ENCODERS);
        waitForNextHardwareCycle();
        
        //set targets
        hardware.getRightDriveMotor().setTargetPosition(encoderCounts);
        hardware.getRightDriveMotor().setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        hardware.getLeftDriveMotor().setTargetPosition(encoderCounts);
        hardware.getLeftDriveMotor().setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        waitForNextHardwareCycle();
        
        //start motors with proper direction
        if (inches > 0) {
            hardware.getRightDriveMotor().setPower(driveSpeed);
            hardware.getLeftDriveMotor().setPower(driveSpeed);
        } else {
            hardware.getRightDriveMotor().setPower(-driveSpeed);
            hardware.getLeftDriveMotor().setPower(-driveSpeed);
        }
        
        //wait for motors to reach positions
        while(hardware.getRightDriveMotor().getCurrentPosition() < encoderCounts || hardware.getLeftDriveMotor().getCurrentPosition() < encoderCounts)
            waitOneFullHardwareCycle();
    }

    private void autoTurn(double degrees) throws InterruptedException{
        int encoderCounts = (int)(ENCODER_COUNTS_PER_ROTATION/INCHES_PER_ROTATION*degrees*Math.PI/180*6.0);
        hardware.getRightDriveMotor().setMode(DcMotorController.RunMode.RESET_ENCODERS);
        hardware.getLeftDriveMotor().setMode(DcMotorController.RunMode.RESET_ENCODERS);
        waitForNextHardwareCycle();
        
        //set targets
        hardware.getRightDriveMotor().setTargetPosition(encoderCounts);
        hardware.getRightDriveMotor().setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        hardware.getLeftDriveMotor().setTargetPosition(encoderCounts);
        hardware.getLeftDriveMotor().setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        waitForNextHardwareCycle();
        
        //start motors with proper direction
        if (degrees > 0) { //make sure to negate these as necessary so that the following code turns the robot clockwise
            hardware.getRightDriveMotor().setPower(driveSpeed);
            hardware.getLeftDriveMotor().setPower(driveSpeed);
        } else {
            hardware.getRightDriveMotor().setPower(-driveSpeed);
            hardware.getLeftDriveMotor().setPower(-driveSpeed);
        }
        
        //wait for motors to reach positions
        while(hardware.getRightDriveMotor().getCurrentPosition() < encoderCounts || hardware.getLeftDriveMotor().getCurrentPosition() < encoderCounts)
            waitOneFullHardwareCycle();
    }
}