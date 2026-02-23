import { Link } from "expo-router";
import { ScrollView, Text, Pressable } from "react-native";
import { HomeIcon } from "../components/Icons";

export default function About() {
  return (
    <ScrollView className="mt-24">
      <Link asChild href="/">
        <Pressable>
          <HomeIcon />
        </Pressable>
      </Link>
      <Text className="text-white font-bold mb-8 text-2xl">
        Sobre el proyecto
      </Text>
      <Text className="text-white">
        Lorem ipsum es el texto que se usa habitualmente en diseño gráfico en
        demostraciones de tipografías o de bocetos para diseños para probar el
        arte visual antes de insertar el texto final. Su uso en algunos editores
      </Text>
      <Text className="text-white">
        Lorem ipsum es el texto que se usa habitualmente en diseño gráfico en
        demostraciones de tipografías o de bocetos para diseños para probar el
        arte visual antes de insertar el texto final. Su uso en algunos editores
      </Text>
      <Text className="text-white">
        Lorem ipsum es el texto que se usa habitualmente en diseño gráfico en
        demostraciones de tipografías o de bocetos para diseños para probar el
        arte visual antes de insertar el texto final. Su uso en algunos editores
      </Text>
      <Text className="text-white">
        Lorem ipsum es el texto que se usa habitualmente en diseño gráfico en
        demostraciones de tipografías o de bocetos para diseños para probar el
        arte visual antes de insertar el texto final. Su uso en algunos editores
      </Text>
    </ScrollView>
  );
}
