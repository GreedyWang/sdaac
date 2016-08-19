package sdaac.wym.dms.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 负责计算公式
 * 
 * @author SA1KV5
 * 
 */
public class Calc {

	// ---------公共方法-----------------------------
	/**
	 * 得到最大值
	 * 
	 * @param acts
	 * @param indicateIndex
	 * @param indicateSize
	 * @param uidSize
	 * @return
	 */
	private float getMax(Float[] acts, int indicateIndex, int indicateSize,
			int uidSize) {
		float max = acts[indicateIndex];
		// 计算Max Actual
		for (int k = 0; k < uidSize; k++) {

			if (max < acts[k * indicateSize + indicateIndex])
				max = acts[k * indicateSize + indicateIndex];
		}
		if (max == 0)
			max = 1;
		return max;
	}

	/**
	 * 得到最大值带系数
	 * 
	 * @param acts
	 * @param indicateIndex
	 * @param indicateSize
	 * @param uidSize
	 * @return
	 */
	private float getMaxWithXiShu(Float[] acts,Float[] xishu, int indicateIndex, int indicateSize,
			int uidSize) {
		float max = acts[indicateIndex]*xishu[indicateSize];
		// 计算Max Actual
		for (int k = 0; k < uidSize; k++) {

			if (max < acts[k * indicateSize + indicateIndex]*xishu[k * indicateSize + indicateIndex])
				max = acts[k * indicateSize + indicateIndex]*xishu[k * indicateSize + indicateIndex];
		}
		if (max == 0)
			max = 1;
		return max;
	}
	
	/**
	 * 得到最小值
	 * 
	 * @param acts
	 * @param indicateIndex
	 * @param indicateSize
	 * @param uidSize
	 * @return
	 */
	private float getMin(Float[] acts, int indicateIndex, int indicateSize,
			int uidSize) {
		float min = acts[indicateIndex];
		// 计算Max Actual
		for (int k = 0; k < uidSize; k++) {

			if (min > acts[k * indicateSize + indicateIndex])
				min = acts[k * indicateSize + indicateIndex];
		}
		if (min == 0)
			min = 1;
		return min;
	}

	/**
	 * 有多个指标计算后再得到最小值
	 * 
	 * @param acts
	 * @param indicateIndex
	 * @param indicateSize
	 * @param uidSize
	 * @return
	 */
	private float getMinWithMultiInputsBy3(Float[] acts, int indicateIndex,
			int indicateSize, int uidSize) {

		// z = (float) (act1 * 0.2 + act2 * 0.4 + act3 * 0.4);
		List<Double> actlist = new ArrayList<Double>();
		for (int k = 0; k < uidSize; k++) {
			actlist.add(0.2 * acts[k * indicateSize + indicateIndex - 2] + 0.4
					* acts[k * indicateSize + indicateIndex - 1] + 0.4
					* acts[k * indicateSize + indicateIndex]);
		}
		float min = 0;
		if (!actlist.isEmpty()) {
			min = actlist.get(0).floatValue();
		}
		// 计算Max Actual
		for (int k = 0; k < actlist.size(); k++) {

			if (min > actlist.get(k))
				min = actlist.get(k).floatValue();
		}
		if (min == 0)
			min = 1;
		return min;
	}

	/**
	 * 有多个指标计算后再得到最小值
	 * 
	 * @param acts
	 * @param indicateIndex
	 * @param indicateSize
	 * @param uidSize
	 * @return
	 */
	private float getMinWithMultiInputsBy2(Float[] acts, int indicateIndex,
			int indicateSize, int uidSize) {
		
		// z = (float) (act2 * 0.8 + act3 * 0.2);
		List<Double> actlist = new ArrayList<Double>();
		for (int k = 0; k < uidSize; k++) {
			actlist.add(0.8* acts[k * indicateSize + indicateIndex - 1] + 0.2
					* acts[k * indicateSize + indicateIndex]);
		}
		float min = actlist.get(0).floatValue();
		// 计算Max Actual
		for (int k = 0; k < actlist.size(); k++) {

			if (min > actlist.get(k))
				min = actlist.get(k).floatValue();
		}
		if (min == 0)
			min = 1;
		return min;
	}
	//-------------------------区域到大组
	
//	/**
//	 * 区域到大组
//	 * 指标 ： 质量
//	 * @param act1
//	 * @param act2
//	 * @param act3
//	 * @param weight
//	 * @param acts
//	 * @param target
//	 * @param indicateSize
//	 * @param uidSize
//	 * @param indicateIndex
//	 * @return
//	 */
//	public float doCalc2(float act1, float act2, float act3, float weight,
//			Float[] acts, float target, int indicateSize, int uidSize,
//			int indicateIndex) {
//		float z = weight;//
//		if (act1 > 0) {
//			z = 0;
//		} else {
//			z = getMin(acts, indicateIndex, indicateSize, uidSize);
//			if(z<=0) return z; 
//		}
//		float max = getMax(acts, indicateIndex, indicateSize, uidSize);
//		if (act3 < target)
//			return 0;
//		else if (act3 >= target + (1 - target) / 2)
//			return act3 / (max) * z;
//		else
//			return act3 / (max) * z - 5;
//	}


	/*
	 * 如果实际值超过0，就即为0分
	 */
	public float doCalc1(float act1,float act2 ,float weight) {
		if (act1 > 0)
			return 0.0f;
		else if(act2>0){
			return 0f;
		}else{
			return weight;
		}		
	}

	/**
	 * 总体算法=（1-报废率）*分段基数 Z>X Score=0 X/2<Z≤ X Score=（1-Z/系数）*A-5 Z≤ X/2
	 * Score=（1-Z/系数）*A Score可为负值
	 * 
	 * @param act
	 * @param target
	 * @return
	 */
	public float doCalc4(float act, float target, float weight) {
		if (act > target)
			return 0;
		else if (act > target / 2)
			return (1 - act / target) * weight - 5;
		else
			return (1 - act / target) * weight;
	}

	/**
	 * 一次合格率 y≥ W设定值+（ 1-设定值）/2 Score=W1*系数/max(W1*系数…Wn*系数)*Z W设定值≤ W实际值<W设定值+（
	 * 1-设定值）/2 Score=W1*系数/max(X1*系数…XWn*系数)*Z-5 W实际 值< W设定值 Score=0 Score可为负值
	 * 
	 * @param act
	 * @param target
	 * @param score
	 * @param acts
	 * @return
	 */
	public float doCalc3(float act, float target, Float[] acts,
			int indicateSize, int uidSize, float lastResult, int indicateIndex) {
		float max = getMax(acts, indicateIndex, indicateSize, uidSize);
		if (act < target)
			return 0;
		else if (act >= target + (1 - target) / 2)
			return act / (max) * lastResult;
		else
			return act / (max) * lastResult - 5;

	}

	public float doCalc9(float act, Float[] acts, int indicateSize,
			int uidSize, float lastResult, int indicateIndex) {
		if (lastResult < 1)
			return 0;

		return (act + 1) / getMin(acts, indicateIndex, indicateSize, uidSize)
				* lastResult;
	}

	/**
	 * " y=y+1 Z=Z/(y1/min(y1….yn))" Score一旦为0，FTTQ将不参与计算
	 * 
	 * @param act
	 * @param min
	 * @param lastResult
	 * @return
	 */
	public float doCalc2(float act, Float[] acts, int indicateSize,
			int uidSize, float lastResult, int indicateIndex) {
		if (lastResult < 1)
			return 0;

		return (act + 1) / getMin(acts, indicateIndex, indicateSize, uidSize)
				* lastResult;
	}

	public float doCalc5(float act, float weight) {

		return weight - act * 5;
	}

	/**
	 * 区域->大组 "定额完成率 SA" "生产 Volumn " "定额完成率 SA" A= 20 X≥ 95%（以开放的形式输入设定值）
	 * Monthly 以实际值/分段最优值*基数值 Z≥X设定值+（1-X设定值）/2 score=X1/max(X1…Xn)*A 20% X设定值≤
	 * Z < X设定值+（1-X设定值）/2 score=X1/max(X1…Xn)*A-5 Z< X(设定值） 0
	 * 
	 * @param act
	 * @param acts
	 * @param indicateSize
	 * @param uidSize
	 * @param lastResult
	 * @return
	 */
	public float doCalc6(float act, float target, float weight, Float[] acts,
			int indicateSize, int uidSize, int indicateIndex) {
		if (act < target) {
			return 0;
		}
		if (act >= target + (1 - target) / 2) {
			return act * weight
					/ getMax(acts, indicateIndex, indicateSize, uidSize);
		}
		if (act < target + (1 - target) / 2) {
			return act * weight
					/ getMax(acts, indicateIndex, indicateSize, uidSize) - 5;
		}
		return 0;
	}

	/**
	 * 区域->大组 "成本 Cost" "报废 Scrap" A = 25 "X ≤ 3%（以开放的形式输入设定值）
	 * 注意此项有报废系数（以开放的形式输入系数）" Monthly 总体算法=（1-报废率）*分段基数 Z>X Score=0 25% X/2<Z≤
	 * X Score=（1-Z/系数）*A-5 Z≤ X/2 Score=（1-Z/系数）*A Score可为负值
	 * 
	 * @param act
	 * @param acts
	 * @param indicateSize
	 * @param uidSize
	 * @param lastResult
	 * @return
	 */
	public float doCalc7(float act, float target,float xishu, float weight) {
		if(target==0)return 0;
		if (act > target) {
			return 0;
		}
		if (act <= target && act > target / 2) {
			return (1 - act / target) * weight - 5;
		}
		if (act <= target / 2) {
			return (1 - act / target) * weight;

		}
		return 0;
	}

	/**
	 * 区域->大组 加分项
	 * 
	 * @param act
	 * @return
	 */
	public float doCalc8(float act) {

		return act;
	}

	/**
	 * 区域->大组 管理评审 "X≥1 otherwise X<1" "Z=0 Z=A" "y=y+1 系数=0.8 W=w+1 系数=0.2
	 * K=Y*系数+W*系数 Score=Z/(K1/min(K1….Kn))"
	 * 
	 * @param act
	 * @return
	 */
	public float doCalc9(float act, float act2, float act3) {
		if (act > 0)
			return 0;

		return act;
	}

	// ---------- //大组长->小组---------
	/**
	 * "安全 Safety"
	 * 
	 * "有工时损失事故/无工时损失事故 LTA or RCA" A=10 x=0 次 Monthly
	 * "如果有一次有工时损失事故/无工时损失事故,安全分为0分，
	 * 
	 * 不安全行为以统计方法求值(分值/(实际值/不安全行为最小值))" if X≥1次 Z=0 if X=0次 Z=A "不安全行为 Unsafe
	 * Behaviors" y=0次 Monthly " y=y+1 Score=Z/(y1/min(y1….yn))"
	 */
	public float doCalc21(float act1, float act2, float weight, Float[] acts,
			int indicateSize, int uidSize, int indicateIndex) {

		float z = weight;//
		if (act1 > 0) {
			z = 0;
			return z;
		} else {
			return z / (act2 + 1)
					* getMin(acts, indicateIndex, indicateSize, uidSize);
		}
	}

	/**
	 * "质量 Quality" "外部客户抱怨 WFCC" A=20 X 外部= 0次 Monthly "如果有一次正式客户抱怨,质量分为0分.
	 * 
	 * 内部抱怨以统计方法求值(分值/(实际值/抱怨最小值)) FTTQ以统计方法求值（以实际值/分段最优值*基数值）" "if X ≥1次 Other
	 * wise X<1次" "Z=0 Z=A" "内部客户抱怨 Internal CC" Y内部 = 0次 Monthly "y=y+1
	 * Z=Z/(y1/min(y1….yn))" Score一旦为0，FTTQ将不参与计算
	 * 
	 * "一次合格率 FTTQ" "W ≥ 95%（以开放的形式输入设定值） 注意此项有质量系数（以开放的形式输入系数）" Monthly y≥
	 * W设定值+（ 1-设定值）/2 Score=W1*系数/max(W1*系数…Wn*系数)*Z W设定值≤ W实际值<W设定值+（
	 * 1-设定值）/2 Score=W1*系数/max(X1*系数…XWn*系数)*Z-5 W实际 值< W设定值 Score=0 Score可为负值
	 * 
	 * @param act1
	 * @param act2
	 * @param act3
	 * @param weight
	 * @param acts
	 * @param target
	 * @param indicateSize
	 * @param uidSize
	 * @param indicateIndex
	 * @return float 得分结果
	 */
	public float doCalc22(float act1, float act2, float act3, float weight,
			Float[] acts, float target, int indicateSize, int uidSize,
			int indicateIndex,Float[] xishu,float myxishu) {
		float z = weight;//
		if (act1 > 0) {
			z = 0;
			return z;
		} else {
			if (act2 == 0) {
				z = z * getMin(acts, indicateIndex, indicateSize, uidSize);
			} else {
				z = z
						/ act2
						* getMin(acts, indicateIndex - 1, indicateSize, uidSize);
			}

		}
		float max = getMaxWithXiShu(acts,xishu, indicateIndex, indicateSize, uidSize);
		if (act3 < target)
			return 0;
		else if (act3 >= target + (1 - target) / 2)
			return act3*myxishu / (max) * z;
		else
			return act3*myxishu / (max) * z - 5;
	}

	/**
	 * "出勤 Absence" A=10 X年假 Monthly 以统计方法求值(分值/(实际值/缺勤最小值)) "X= X+1 系数=0.2
	 * Y=Y+1 系数= 0.4 W=W+1 系数=0.4 Z=X*系数+Y*系数+W*系数 Score=A/(Z1/min(Z1….Zn))" Y病假
	 * W事假
	 * 
	 * @param act1
	 * @param act2
	 * @param act3
	 * @param weight
	 * @param acts
	 * @param indicateSize
	 * @param uidSize
	 * @param indicateIndex
	 * @return
	 */
	public float doCalc23(float act1, float act2, float act3, float weight,
			Float[] acts, int indicateSize, int uidSize, int indicateIndex) {
		float z;
		z = (float) (act1 * 0.2 + act2 * 0.4 + act3 * 0.4);
		
		if (z == 0) {
			return weight
		
			* getMinWithMultiInputsBy3(acts, indicateIndex, indicateSize,
					uidSize);

		} else {
			return weight / z
			* getMinWithMultiInputsBy3(acts, indicateIndex, indicateSize,
					uidSize);
		}

	}

	/**
	 * "纪律 Displine" A=15 X厂级 = 0次 Monthly "只要有一次上升至工厂层面的处分纪律分为0，
	 * 普通纪律问题以及谈话记录以统计方法求值(分值/(实际值/纪律次数最小值))" "X≥1 otherwise X<1" "Z=0 Z=A" 15%
	 * Y谈话记录=0次 "y= y+1 系数=0.8 w=w+1 系数=0.2 K=Y*系数+W*系数
	 * Score=Z/(K1/min(K1….Kn))" W其它=0次
	 * 
	 * 
	 * @param act1
	 * @param act2
	 * @param act3
	 * @param weight
	 * @param acts
	 * @param indicateSize
	 * @param uidSize
	 * @param indicateIndex
	 * @return
	 */
	public float doCalc24(float act1, float act2, float act3, float weight,
			Float[] acts, int indicateSize, int uidSize, int indicateIndex) {
		if (act1 > 0) {
			return 0;

		} else {
			float z;
			z = (float) (act3 * 0.2 + act2 * 0.8);

			if (z == 0) {
				return weight

						* getMinWithMultiInputsBy2(acts, indicateIndex,
								indicateSize, uidSize);

			} else {
				System.out.println(getMinWithMultiInputsBy2(acts, indicateIndex,
								indicateSize, uidSize));
				return weight
						/ z
						* getMinWithMultiInputsBy2(acts, indicateIndex,
								indicateSize, uidSize);
			}

		}

	}

	/**
	 * 5S A=15 X =0次 Monthly 以统计方法求值(分值/(实际值/5S次数最小值)) "X=X+1
	 * Score=A/(X1/min(X1….Xn))"
	 * 
	 * @param act1
	 * @param act2
	 * @param act3
	 * @param weight
	 * @param acts
	 * @param indicateSize
	 * @param uidSize
	 * @param indicateIndex
	 * @return
	 */
	public float doCalc25(float act1, float weight, Float[] acts,
			int indicateSize, int uidSize, int indicateIndex) {
		if (act1 == 0) {
			return weight

			* getMin(acts, indicateIndex, indicateSize, uidSize);
		} else {
			return weight / act1
					* getMin(acts, indicateIndex, indicateSize, uidSize);
		}
	}
	
	/**
	 * 小组-》员工 
	 * 指标 ： 安全
	 * @param act1
	 * @param act2
	 * @param weight
	 * @return
	 */
	public float doCalc41(float act1, float act2, float weight) {
		float z = 0;//
		if(act1 == 0){	
			z=weight;
		}		
		return z-act2;		
	}
	/**
	 * 小组-》员工 
	 * 指标 ： 质量
	 * @param act1
	 * @param act2
	 * @param act3
	 * @param weight
	 * @param acts
	 * @param target
	 * @param indicateSize
	 * @param uidSize
	 * @param indicateIndex
	 * @return
	 */
	public float doCalc42(float act1, float act2, float act3, float weight,
			Float[] acts, float target, int indicateSize, int uidSize,
			int indicateIndex) {
		float z = weight;//
		if (act1 > 0) {
			z = 0;
			return 0;
		} else {
			z = z-act2*5;
			if(z<=0) return z; 
		}
		float max = getMax(acts, indicateIndex, indicateSize, uidSize);
		if (act3 < target)
			return 0;
		else if (act3 >= target + (1 - target) / 2)
			return act3 / (max) * z;
		else
			return act3 / (max) * z - 5;
	}
	/**
	 * 小组-》员工 
	 * 指标 ： 报废
	 * @param act
	 * @param target
	 * @param weight
	 * @return
	 */
	public float doCalc43(float act, float target, float weight) {
		if (act <= target) {
			return weight;
		}else{
			return weight+target-act;
		}		
	}

	/**
	 * 小组-》员工 
	 * 指标 ： 出勤
	 * //	X>0	Z1=X*-1
		//	Y>0	Z2=Y*-2
		//	W>0	Z3=W*-2
		//	If X+Y+W>11 则 此人Total score=0	
		//	Score=A+Z1+Z2+Z3（score 可以为负值）	
	 * @param act1
	 * @param act2
	 * @param act3
	 * @param weight
	 * @return
	 */
	public float doCalc44(float act1, float act2, float act3, float weight) {
		int x,y,z;
		x=(int) (-1*act1);
		y=(int) (-2*act2);
		z = (int) (-2*act3);
		if(act1+act2+act3>11) return -100;
		else{
			return weight+x+y+z;
		}
		
	}
	
	/**
	 * 小组-》员工 
	 * 指标 ： 纪律
	 * @param act1
	 * @param act2
	 * @param act3
	 * @param weight
	 * @return
	 */
	public float doCalc45(float act1, float act2, float act3, float weight) {
		float z = weight;
		if(act1>=1) z = 0;
		else{
			z=z-act2*5-act3;
		}
		return z;
	}
	
	/**
	 * 小组-》员工 
	 * 5s
	 * @param act
	 * @param weight
	 * @return
	 */
	public float doCalc46(float act, float weight) {
		return 	weight-act;
	}

}
