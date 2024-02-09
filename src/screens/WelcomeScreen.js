import { useNavigation } from "@react-navigation/native";
import { LinearGradient } from "expo-linear-gradient";
import React from "react";
import { View, Text, TouchableOpacity } from "react-native";
import {
  widthPercentageToDP as wp,
  heightPercentageToDP as hp,
} from "react-native-responsive-screen";

export default function Welcome() {
  const navigation = useNavigation();

  return (
    <View
      // source={require("../../assets/images/welcome/reporter.jpg")}
      className="flex-1 justify-center items-center pb-6 bg-green-900"
    >
      <LinearGradient
        colors={["transparent", "rgba(0,0,0,0.9)"]}
        style={{
          position: "absolute",
          bottom: 0,
          width: "100%",
          height: "100%",
        }}
        start={{ x: 1, y: 0 }}
        end={{ x: 1, y: 1 }}
      />
      <View className="flex-1 items-center justify-end max-w-[85%]  space-y-4 ">
        <Text
          className="font-bold text-4xl shadow-2xl text-white text-center tracking-wider"
          style={{
            fontSize: wp(8),
            fontFamily: "SpaceGroteskBold",
          }}
        >
          Stay Informed from Day One
        </Text>
        <Text
          className="font-bold text-white text-center max-w-[85%] leading-12 tracking-wider"
          style={{
            fontSize: wp(4),
            fontFamily: "SpaceGroteskMedium",
          }}
        >
          Discover the Latest News with our Seamless Onboarding Experience.
        </Text>
      </View>

      <TouchableOpacity
        className="bg-white rounded-full p-4 justify-center items-center w-[90%] mt-8"
        onPress={() => navigation.navigate("HomeTabs")}
      >
        <Text className="text-base text-green-900 font-medium ">
          Getting Started
        </Text>
      </TouchableOpacity>
    </View>
  );
}
