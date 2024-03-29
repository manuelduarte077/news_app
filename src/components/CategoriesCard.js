import React from "react";
import { View, Text, ScrollView, TouchableOpacity } from "react-native";
import { heightPercentageToDP as hp } from "react-native-responsive-screen";

export default function CategoriesCard({
  categories,
  activeCategory,
  handleChangeCategory,
}) {
  return (
    <View>
      <ScrollView
        horizontal
        showsHorizontalScrollIndicator={false}
        className="space-x-4"
        contentContainerStyle={{
          paddingRight: 20,
        }}
      >
        {categories.map((category, index) => {
          const isActive = category.title === activeCategory;
          const activeButtonClass = isActive
            ? "bg-black"
            : "bg-black/10 dark:bg-neutral-400 ";
          const activeTextClass = isActive
            ? "text-white"
            : "text-gray-600 dark:text-neutral-600 ";

          return (
            <TouchableOpacity
              key={index}
              onPress={() => handleChangeCategory(category.title)}
              className="flex items-center space-y-1"
            >
              <View className={"rounded-full py-2 px-4 " + activeButtonClass}>
                <Text
                  className={"capitalize " + activeTextClass}
                  style={{
                    fontSize: hp(1.6),
                  }}
                >
                  {category.title}
                </Text>
              </View>
            </TouchableOpacity>
          );
        })}
      </ScrollView>
    </View>
  );
}
