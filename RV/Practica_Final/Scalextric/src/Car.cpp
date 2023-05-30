#include "Car.h"
#include "Car_pieces.h"
#include <GL/glew.h>
#include "Object.h"
#include "Piece.h"

Car::Car()
{
	Material* mtl0 = new Material();
	mtl0->SetAmbientReflect(0.0f, 0.0f, 0.0f);
	mtl0->SetDifusseReflect(1.0f, 1.0f, 1.0f);
	mtl0->SetSpecularReflect(0.0f, 0.0f, 0.0f);
	mtl0->SetShininess(2.0f);
	mtl0->InitTexture("textures/re0_main.tga");

	Material* mtl1 = new Material();
	mtl1->SetAmbientReflect(0.0f, 0.0f, 0.0f);
	mtl1->SetDifusseReflect(1.0f, 1.0f, 1.0f);
	mtl1->SetSpecularReflect(0.0f, 0.0f, 0.0f);
	mtl1->SetShininess(2.0f);
	mtl1->InitTexture("textures/generic_main.tga");

	Material* mtl2 = new Material();
	mtl2->SetAmbientReflect(0.0f, 0.0f, 0.0f);
	mtl2->SetDifusseReflect(1.0f, 1.0f, 1.0f);
	mtl2->SetSpecularReflect(0.0f, 0.0f, 0.0f);
	mtl2->SetShininess(2.0f);
	mtl2->InitTexture("textures/re0_wheel.tga");

	Material* mtl3 = new Material();
	mtl3->SetAmbientReflect(0.0f, 0.0f, 0.0f);
	mtl3->SetDifusseReflect(1.0f, 1.0f, 1.0f);
	mtl3->SetSpecularReflect(0.0f, 0.0f, 0.0f);
	mtl3->SetShininess(2.0f);
	mtl3->InitTexture("textures/light_glow.tga");

	Material* mtl4 = new Material();
	mtl4->SetAmbientReflect(0.0f, 0.0f, 0.0f);
	mtl4->SetDifusseReflect(1.0f, 1.0f, 1.0f);
	mtl4->SetSpecularReflect(0.0f, 0.0f, 0.0f);
	mtl4->SetShininess(2.0f);
	mtl4->InitTexture("textures/tyre.tga");

	Material* mtl5 = new Material();
	mtl5->SetAmbientReflect(0.0f, 0.0f, 0.0f);
	mtl5->SetDifusseReflect(1.0f, 1.0f, 1.0f);
	mtl5->SetSpecularReflect(0.0f, 0.0f, 0.0f);
	mtl5->SetShininess(2.0f);
	mtl5->InitTexture("textures/tread_slick.tga");

	Material* mtl6 = new Material();
	mtl6->SetAmbientReflect(0.0f, 0.0f, 0.0f);
	mtl6->SetDifusseReflect(1.0f, 1.0f, 1.0f);
	mtl6->SetSpecularReflect(0.0f, 0.0f, 0.0f);
	mtl6->SetShininess(2.0f);
	mtl6->InitTexture("textures/glass.tga");

	pieces[0] = new Car_0(mtl0);
	pieces[1] = new Car_1(mtl1);
	pieces[2] = new Car_2(mtl1);
	pieces[3] = new Car_3(mtl1);
	pieces[4] = new Car_4(mtl1);
	pieces[5] = new Car_5(mtl1);
	pieces[6] = new Car_6(mtl1);
	pieces[7] = new Car_7(mtl1);
	pieces[8] = new Car_8(mtl1);
	pieces[9] = new Car_9(mtl1);
	pieces[10] = new Car_10(mtl1);
	pieces[11] = new Car_11(mtl1);
	pieces[12] = new Car_12(mtl1);
	pieces[13] = new Car_13(mtl0);
	pieces[14] = new Car_14(mtl0);
	pieces[15] = new Car_15(mtl0);
	pieces[16] = new Car_16(mtl2);
	pieces[17] = new Car_17(mtl2);
	pieces[18] = new Car_18(mtl2);
	pieces[19] = new Car_19(mtl2);
	pieces[20] = new Car_20(mtl0);
	pieces[21] = new Car_21(mtl0);
	pieces[22] = new Car_22(mtl0);
	pieces[23] = new Car_23(mtl0);
	pieces[24] = new Car_24(mtl0);
	pieces[25] = new Car_25(mtl3);
	pieces[26] = new Car_26(mtl1);
	pieces[27] = new Car_27(mtl1);
	pieces[28] = new Car_28(mtl1);
	pieces[29] = new Car_29(mtl1);
	pieces[30] = new Car_30(mtl0);
	pieces[31] = new Car_31(mtl0);
	pieces[32] = new Car_32(mtl0);
	pieces[33] = new Car_33(mtl0);
	pieces[34] = new Car_34(mtl0);
	pieces[35] = new Car_35(mtl0);
	pieces[36] = new Car_36(mtl1);
	pieces[37] = new Car_37(mtl1);
	pieces[38] = new Car_38(mtl1);
	pieces[39] = new Car_39(mtl1);
	pieces[40] = new Car_40(mtl0);
	pieces[41] = new Car_41(mtl1);
	pieces[42] = new Car_42(mtl1);
	pieces[43] = new Car_43(mtl1);
	pieces[44] = new Car_44(mtl1);
	pieces[45] = new Car_45(mtl4);
	pieces[46] = new Car_46(mtl5);
	pieces[47] = new Car_47(mtl4);
	pieces[48] = new Car_48(mtl5);
	pieces[49] = new Car_49(mtl4);
	pieces[50] = new Car_50(mtl5);
	pieces[51] = new Car_51(mtl4);
	pieces[52] = new Car_52(mtl5);
	pieces[53] = new Car_53(mtl0);
	pieces[54] = new Car_54(mtl0);
	pieces[55] = new Car_55(mtl6);
	pieces[56] = new Car_56(mtl1);
	pieces[57] = new Car_57(mtl0);
	pieces[58] = new Car_58(mtl0);
	pieces[59] = new Car_59(mtl0);
	pieces[60] = new Car_60(mtl0);
	pieces[61] = new Car_61(mtl0);
	pieces[62] = new Car_62(mtl0);
	pieces[63] = new Car_63(mtl0);
	pieces[64] = new Car_64(mtl0);
	pieces[65] = new Car_65(mtl0);
	pieces[66] = new Car_66(mtl0);
	pieces[67] = new Car_67(mtl0);
	pieces[68] = new Car_68(mtl1);
}

Car::~Car()
{
	for (int i = 0; i < 69; i++) delete pieces[i];
}

int Car::GetNumPieces()
{
	return 69;
}

Piece* Car::GetPiece(int index)
{
	return pieces[index];
}

