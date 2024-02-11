import { useNavigation } from "@react-navigation/native";
import React from "react";
import { View, Text, TouchableOpacity } from "react-native";
import { widthPercentageToDP as wp } from "react-native-responsive-screen";

export default function Welcome() {
  const navigation = useNavigation();

  return (
    <View className="flex-1 justify-center items-center pb-6 bg-[#180E19]">
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
        <Text className="text-base text-[#180E19] font-medium ">
          Getting Started
        </Text>
      </TouchableOpacity>
    </View>
  );
}
