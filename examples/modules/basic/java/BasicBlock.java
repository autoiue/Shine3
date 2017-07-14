package com.example.basic;

import procsynth.shine3.engine.*;
import procsynth.shine3.*;
import java.io.PrintWriter;

public final class BasicBlock extends Block{
	public BasicBlock(){
		this.displayName = "module";
	}

	public void tick(){
		if(Shine3.engine.getTicks() % 200 == 0){
			System.out.println(Shine3.engine.getTicks()+" TICKS AND COUNTING...");
		}
	}
}