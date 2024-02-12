import Ionicons from "@expo/vector-icons/Ionicons";
import AsyncStorage from "@react-native-async-storage/async-storage";
import { useNavigation, useRoute } from "@react-navigation/native";
import React, { useEffect, useState } from "react";
import {
  View,
  ActivityIndicator,
  TouchableOpacity,
  Dimensions,
  Share,
  Alert,
} from "react-native";
import { WebView } from "react-native-webview";

const { height, width } = Dimensions.get("window");

export default function NewsDetails() {
  const { params: item } = useRoute();
  const [visible, setVisible] = useState(false);

  const navigation = useNavigation();
  const [isBookmarked, toggleBookmark] = useState(false);

  const toggleBookmarkAndSave = async () => {
    try {
      // Check if News Article is already in Storage
      const savedArticles = await AsyncStorage.getItem("savedArticles");
      const savedArticlesArray = savedArticles ? JSON.parse(savedArticles) : [];

      // Check if the article is already in the bookmarked list
      const isArticleBookmarked = savedArticlesArray.some(
        (savedArticle) => savedArticle.url === item.url,
      );

      if (!isArticleBookmarked) {
        // If the article is not bookmarked, add it to the bookmarked list
        savedArticlesArray.push(item);
        await AsyncStorage.setItem(
          "savedArticles",
          JSON.stringify(savedArticlesArray),
        );
        toggleBookmark(true);
        // console.log("Article is bookmarked");
      } else {
        // If the article is already bookmarked, remove it from the list
        const updatedSavedArticlesArray = savedArticlesArray.filter(
          (savedArticle) => savedArticle.url !== item.url,
        );
        await AsyncStorage.setItem(
          "savedArticles",
          JSON.stringify(updatedSavedArticlesArray),
        );
        toggleBookmark(false);
        // console.log("Article is removed from bookmarks");
      }
    } catch (error) {
      console.log("Error Saving Article", error);
    }
  };

  // Sahre the news article
  const shareArticle = async () => {
    try {
      await Share.share({
        message: `Checkout this news article: ${item.title} \n${item.url}`,
        title: item.title,
        url: item.url,
      });
    } catch (error) {
      Alert.alert(error.message);
    }
  };

  useEffect(() => {
    // Load saved articles from AsyncStorage when the component mounts
    const loadSavedArticles = async () => {
      try {
        const savedArticles = await AsyncStorage.getItem("savedArticles");
        const savedArticlesArray = savedArticles
          ? JSON.parse(savedArticles)
          : [];

        // Check if the article is already in the bookmarked list
        const isArticleBookmarked = savedArticlesArray.some(
          (savedArticle) => savedArticle.url === item.url,
        );

        toggleBookmark(isArticleBookmarked);
        // console.log("Check if the current article is in bookmarks");
      } catch (error) {
        console.log("Error Loading Saved Articles", error);
      }
    };

    loadSavedArticles();
  }, [item.url]);

  return (
    <>
      <View className="w-full flex-row justify-between items-center px-4 pt-10 pb-4 bg-white">
        <TouchableOpacity onPress={() => navigation.goBack()}>
          <Ionicons name="chevron-back" size={24} color="black" />
        </TouchableOpacity>

        <View className="space-x-3 rounded-full items-center justify-center flex-row">
          <TouchableOpacity onPress={toggleBookmarkAndSave}>
            <Ionicons
              name={isBookmarked ? "bookmark" : "bookmark-outline"}
              size={24}
              color="black"
            />
          </TouchableOpacity>

          <TouchableOpacity onPress={shareArticle}>
            <Ionicons name="share-outline" size={24} color="black" />
          </TouchableOpacity>
        </View>
      </View>
      {/* WebView */}
      <WebView
        source={{ uri: item.url }}
        onLoadStart={() => setVisible(true)}
        onLoadEnd={() => setVisible(false)}
      />

      {visible && (
        <ActivityIndicator
          size="large"
          color="green"
          style={{
            position: "absolute",
            top: height / 2,
            left: width / 2,
          }}
        />
      )}
    </>
  );
}
