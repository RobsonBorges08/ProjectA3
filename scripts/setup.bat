set JFXSDK_URL=https://download2.gluonhq.com/openjfx/19/openjfx-19_windows-x64_bin-sdk.zip
set JFXSDK_ZIP=./jfxsdk.zip

curl -o %JFXSDK_ZIP% %JFXSDK_URL%
jar xvf %JFXSDK_ZIP%