/**
* The MIT License (MIT)
* 
* Copyright (c) 2015 Shubhrendu Tripathi
* 
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
* 
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
* 
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
* 
**/

public class Task {
	private int Id; 
	private int Hour;
	private int Minute;
	private int Second;
	private boolean AM;
	private String Desc;
	private boolean Checked = false;
	
	public void setId(int Id){
		this.Id = Id;
	}
	
	public int getId(){
		return this.Id;
	}
	
	public void setHour(int Hour){
		this.Hour = Hour;
	}
	
	public int getHour(){
		return this.Hour;
	}
	
	public void setMinute(int Minute){
		this.Minute = Minute;
	}
	
	public int getMinute(){
		return this.Minute;
	}
	
	public void setSecond(int Second){
		this.Second = Second;
	}
	
	public int getSecond(){
		return this.Second;
	}
	
	public void setAM(boolean AM){
		this.AM = AM;
	}
	
	public boolean getAM(){
		return this.AM;
	}
	
	public void setDesc(String Desc){
		this.Desc = Desc;
	}
	
	public String getDesc(){
		return this.Desc;
	}
	
	public void setChecked(boolean Checked){
		this.Checked = Checked;
	}
	
	public boolean getChecked(){
		return this.Checked;
	}
	
    @Override
    public String toString() {
        return 	this.Id + " " +
        		this.Hour + " " +
        		this.Minute + " " +
        		this.Second + " " +
        		this.AM + " " +
        		this.Desc + " " +
        		this.Checked; 
    }
}
