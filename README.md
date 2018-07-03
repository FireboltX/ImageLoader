对主流框架进行封装，可切换glide/picasso/fresco

一，安装：

	Gradle:
		Step 1. Add the JitPack repository to your build file
		Add it in your root build.gradle at the end of repositories:
		allprojects {
			repositories {
				...
				maven { url 'https://jitpack.io' }
			}
		}
		
		Step 2. Add the dependency
		dependencies {
					implementation 'com.github.FireboltX:ImageLoader:0.1'
			}
		
	Maven:			
		Step 1. Add the JitPack repository to your build file
		<repositories>
			<repository>
				<id>jitpack.io</id>
				<url>https://jitpack.io</url>
			</repository>
		</repositories>
		
		Step 2. Add the dependency
		<dependency>
			<groupId>com.github.FireboltX</groupId>
			<artifactId>ImageLoader</artifactId>
			<version>0.1</version>
		</dependency>
		
二，使用：

	Step 1:
	
	初始化图片库
		public class App extends Application {

		@Override
		public void onCreate() {
			super.onCreate();
			//初始化图片库
			//ImageLoader.getInstance().setGlobalImageLoader(new GlideLoader(this));
			ImageLoader.getInstance().setGlobalImageLoader(new PicassoLoader(this));
			}
		}
	Step 2:
	
	使用
		ImageLoader.getInstance()
			.load("http://g.hiphotos.baidu.com/image/pic/item/0823dd54564e92584fbb491f9082d158cdbf4eb0.jpg")
			.into(image);
	
	
	
	
	
