import { Link } from "expo-router";
import { Pressable, ScrollView, Text } from "react-native";
import { HomeIcon } from "../components/Icons";
import { Screen } from "../components/Screen";

export default function About() {
  return (
    <Screen>
      <ScrollView>
        <Link asChild href="/">
          {
            /*
          -- Native way
          <Pressable>
            {
             {({ pressed }) => <HomeIcon style={{ opacity: pressed ? 0.5 : 1 }} />}
          </Pressable>
          */
            // -- Tailwind way
            <Pressable className="active:opacity-50">
              <HomeIcon />
            </Pressable>
          }
        </Link>
        <Text className="text-white font-bold mb-8 text-2xl">
          Sobre el proyecto
        </Text>
        <Text className="text-white">
          Lorem ipsum es el texto que se usa habitualmente en diseño gráfico en
          demostraciones de tipografías o de bocetos para diseños para probar el
          arte visual antes de insertar el texto final. Su uso en algunos
          editores
        </Text>
        <Text className="text-white">
          Lorem ipsum es el texto que se usa habitualmente en diseño gráfico en
          demostraciones de tipografías o de bocetos para diseños para probar el
          arte visual antes de insertar el texto final. Su uso en algunos
          editores
        </Text>
        <Text className="text-white">
          Lorem ipsum es el texto que se usa habitualmente en diseño gráfico en
          demostraciones de tipografías o de bocetos para diseños para probar el
          arte visual antes de insertar el texto final. Su uso en algunos
          editores
        </Text>
        <Text className="text-white">
          Lorem ipsum es el texto que se usa habitualmente en diseño gráfico en
          demostraciones de tipografías o de bocetos para diseños para probar el
          arte visual antes de insertar el texto final. Su uso en algunos
          editores
        </Text>
      </ScrollView>
    </Screen>
  );
}
